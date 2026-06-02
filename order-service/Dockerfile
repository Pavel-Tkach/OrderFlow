FROM gradle:8.14-jdk17-alpine AS build

WORKDIR /app

COPY . .

RUN gradle :order-service:bootJar --no-daemon

FROM eclipse-temurin:17-jdk-alpine

COPY --from=build /app/order-service/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]