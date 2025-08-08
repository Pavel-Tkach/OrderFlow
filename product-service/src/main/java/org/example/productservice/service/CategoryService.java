package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.CategoryDto;
import org.example.productservice.entity.Category;
import org.example.productservice.mapper.CategoryMapper;
import org.example.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public List<CategoryDto> getAll(UUID id) {
        return categoryRepository.findAllWithRecursive(id)
                .stream().map(categoryMapper::toDto)
                .toList();
    }

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        validateParentId(categoryDto.getParentId());
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toDto(savedCategory);
    }

    @Transactional
    public CategoryDto update(CategoryDto categoryDto) {
        validateParentId(categoryDto.getParentId());
        validateCategoryId(categoryDto.getId());
        Category category = categoryMapper.toEntity(categoryDto);
        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.toDto(updatedCategory);
    }

    @Transactional
    public void deleteById(UUID id) {
        List<Category> childAndParentCategories = categoryRepository.findAllWithRecursive(id);
        categoryRepository.deleteAll(childAndParentCategories);
    }

    private void validateParentId(UUID parentId) {
        if (!Objects.isNull(parentId)) {
            categoryRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
        }
    }

    private void validateCategoryId(UUID categoryId) {
        if (!Objects.isNull(categoryId)) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }
    }
}
