package org.example.inventoryservice.mapper;

import org.example.inventoryservice.dto.response.InventoryItemResponseDto;
import org.example.inventoryservice.entity.InventoryItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    InventoryItem toEntity(InventoryItemResponseDto inventoryItemResponseDto);

    InventoryItemResponseDto toDto(InventoryItem inventoryItem);
}
