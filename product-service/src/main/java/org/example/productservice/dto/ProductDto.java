package org.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private UUID id;

    private String sku;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isActive;

    private UUID categoryId;

    private List<ProductAttributeDto> productAttributes;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
