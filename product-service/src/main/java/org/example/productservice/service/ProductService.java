package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ProductDto;
import org.example.productservice.entity.Category;
import org.example.productservice.entity.Product;
import org.example.productservice.mapper.ProductMapper;
import org.example.productservice.repository.CategoryRepository;
import org.example.productservice.repository.ProductRepository;
import org.example.productservice.repository.specification.ProductSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.example.productservice.repository.specification.ProductSpec.hasCategory;
import static org.example.productservice.repository.specification.ProductSpec.priceBetween;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProductsByFilters(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Boolean isActive,
            String sku,
            String name,
            String category
    ) {
        Specification<Product> spec = Stream.of(
                        Optional.ofNullable(minPrice).flatMap(min -> Optional.ofNullable(maxPrice)
                                .map(max -> priceBetween(min, max))
                        ),
                        Optional.ofNullable(isActive).map(ProductSpec::isActive),
                        Optional.ofNullable(sku).map(ProductSpec::hasSku),
                        Optional.ofNullable(name).map(ProductSpec::hasName)
                )
                .flatMap(Optional::stream)
                .reduce(Specification.where(null), Specification::and);

        if (!Objects.isNull(category)) {
            Category byName = categoryRepository.findByName(category);
            List<Category> categories = categoryRepository.findAllWithRecursive(byName.getId());
            spec = spec.and(hasCategory(categories));
        }

        return productRepository.findAll(spec).stream()
                .map(productMapper::toDto)
                .toList();
    }


    @Transactional
    public ProductDto save(ProductDto productDto) {
        validateCategoryId(productDto.getCategoryId());
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(product);

        return productMapper.toDto(savedProduct);
    }

    @Transactional
    public ProductDto update(ProductDto productDto) {
        validateCategoryId(productDto.getCategoryId());
        validateProductId(productDto.getId());
        Product product = productMapper.toEntity(productDto);
        Product updatedProduct = productRepository.save(product);

        return productMapper.toDto(updatedProduct);
    }

    @Transactional
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    private void validateCategoryId(UUID categoryId) {
        if (!Objects.isNull(categoryId)) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }
    }

    private void validateProductId(UUID productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
