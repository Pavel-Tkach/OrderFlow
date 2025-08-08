package org.example.productservice.mapper;

import org.example.productservice.dto.CategoryDto;
import org.example.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "parent.id", target = "parentId")
    CategoryDto toDto(Category category);

    @Mapping(source = "parentId", target = "parent", qualifiedByName = "mapParent")
    Category toEntity(CategoryDto categoryDto);

    @Named("mapParent")
    default Category mapParent(UUID parentId) {
        if (parentId == null) return null;
        Category parent = new Category();
        parent.setId(parentId);
        return parent;
    }
}
