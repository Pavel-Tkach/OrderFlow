package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ProductAttributeDto;
import org.example.productservice.entity.ProductAttribute;
import org.example.productservice.mapper.ProductAttributeMapper;
import org.example.productservice.repository.ProductAttributeRepository;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;
    private final ProductRepository productRepository;
    private final ProductAttributeMapper productAttributeMapper;

    @Transactional
    public ProductAttributeDto save(ProductAttributeDto productAttributeDto) {
        ProductAttribute productAttribute = productAttributeMapper.toEntity(productAttributeDto);
        ProductAttribute savedProductAttribute = productAttributeRepository.save(productAttribute);

        return productAttributeMapper.toDto(savedProductAttribute);
    }

    @Transactional
    public ProductAttributeDto update(ProductAttributeDto productAttributeDto) {
        validateAttributeId(productAttributeDto.getId());
        validateProductId(productAttributeDto.getProductId());
        ProductAttribute productAttribute = productAttributeMapper.toEntity(productAttributeDto);
        ProductAttribute savedProductAttribute = productAttributeRepository.save(productAttribute);

        return productAttributeMapper.toDto(savedProductAttribute);
    }

    @Transactional
    public void deleteById(UUID id) {
        productAttributeRepository.deleteById(id);
    }

    private void validateAttributeId(UUID attributeId) {
        productAttributeRepository.findById(attributeId)
                .orElseThrow(() -> new RuntimeException("Product attribute not found"));
    }

    private void validateProductId(UUID productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
