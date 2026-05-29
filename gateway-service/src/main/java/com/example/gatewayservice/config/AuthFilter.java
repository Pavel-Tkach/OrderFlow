package com.example.gatewayservice.config;

import com.example.gatewayservice.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config {
        private String role;
    }

    @Value("${jwt.public-key}")
    private String publicKey;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authToken = Objects.requireNonNull(exchange.getRequest()
                            .getHeaders()
                            .get(HttpHeaders.AUTHORIZATION)).get(0);
            if (Objects.isNull(authToken)) {
                throw new AuthenticationException("Missing authorization information");
            }
            String[] parts = authToken.split(" ");
            if (parts.length != 2 || !parts[0].equals("Bearer")) {
                throw new AuthenticationException("Incorrect authorization structure");
            }
            String token = parts[1].trim();
            List<String> jwtRoles = getRoles(token);
            checkNeededRoles(jwtRoles, config.role);

            return chain.filter(exchange);
        } ;
    }

    private List<String> getRoles(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Map<String, Object> realmAccess = claims.get("realm_access", Map.class);
        if (realmAccess == null) {
            return List.of();
        }

        return (List<String>) realmAccess.get("roles");
    }

    @SneakyThrows
    public RSAPublicKey getPublicKey() {
        String pem = "-----BEGIN PUBLIC KEY-----\n"
                + publicKey
                + "\n-----END PUBLIC KEY-----";

        String cleaned = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(cleaned);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory factory = KeyFactory.getInstance("RSA");

        return (RSAPublicKey) factory.generatePublic(spec);
    }

    private void checkNeededRoles(List<String> jwtRoles, String neededRole) {
        if (!jwtRoles.contains(neededRole)) {
            throw new AuthenticationException("Needed role " + neededRole);
        }
    }
}
