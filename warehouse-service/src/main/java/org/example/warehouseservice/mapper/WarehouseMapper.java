package org.example.warehouseservice.mapper;

import org.example.warehouseservice.dto.WarehouseDto;
import org.example.warehouseservice.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    WarehouseDto toDto(Warehouse warehouse);

    Warehouse toEntity(WarehouseDto warehouseDto);
}
