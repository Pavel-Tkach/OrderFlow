package org.example.productservice.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductAttributeDto {

    private UUID id;

    private String name;

    private String value;

    private UUID productId;
}
