package org.example.productservice.mapper;

import org.example.productservice.dto.ProductAttributeDto;
import org.example.productservice.entity.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    @Mapping(source = "product.id", target = "productId")
    ProductAttributeDto toDto(ProductAttribute productAttribute);

    @Mapping(source = "productId", target = "product.id")
    ProductAttribute toEntity(ProductAttributeDto productAttributeDto);
}
