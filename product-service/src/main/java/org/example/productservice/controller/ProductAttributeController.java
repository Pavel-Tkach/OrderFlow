package org.example.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ProductAttributeDto;
import org.example.productservice.service.ProductAttributeService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @PostMapping("/productAttributes")
    public ProductAttributeDto addProductAttribute(@RequestBody ProductAttributeDto productAttributeDto) {
        return productAttributeService.save(productAttributeDto);
    }

    @PutMapping("/productAttributes")
    public ProductAttributeDto updateProductAttribute(@RequestBody ProductAttributeDto productAttributeDto) {
        return productAttributeService.update(productAttributeDto);
    }

    @DeleteMapping("/productAttributes/{productAttributeId}")
    public void deleteProductAttribute(@PathVariable UUID productAttributeId) {
        productAttributeService.deleteById(productAttributeId);
    }
}
