package org.example.productservice.repository.specification;

import org.example.productservice.entity.Category;
import org.example.productservice.entity.Category_;
import org.example.productservice.entity.Product;
import org.example.productservice.entity.Product_;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public class ProductSpec {

    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, cb) -> cb.between(root.get(Product_.PRICE), minPrice, maxPrice);
    }

    public static Specification<Product> isActive(Boolean isActive) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.IS_ACTIVE), isActive));
    }

    public static Specification<Product> hasSku(String sku) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.SKU), sku));
    }

    public static Specification<Product> hasName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.NAME), name));
    }

    public static Specification<Product> hasCategory(List<Category> categories) {
        return (root, query, cb) -> root.get(Product_.CATEGORY).in(categories);
    }
}
