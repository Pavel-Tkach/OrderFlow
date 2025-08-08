package org.example.warehouseservice.service;

import lombok.RequiredArgsConstructor;
import org.example.warehouseservice.dto.WarehouseDto;
import org.example.warehouseservice.entity.Warehouse;
import org.example.warehouseservice.mapper.WarehouseMapper;
import org.example.warehouseservice.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Transactional(readOnly = true)
    public List<WarehouseDto> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();

        return warehouses.stream()
                .map(warehouseMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public WarehouseDto getNearestWarehouseByCoordinates(Double latitude, Double longitude) {
        Warehouse nearestWarehouse = warehouseRepository.findNearestWarehouseByCoordinates(latitude, longitude);

        return warehouseMapper.toDto(nearestWarehouse);
    }

    @Transactional
    public WarehouseDto create(WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseMapper.toEntity(warehouseDto);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return warehouseMapper.toDto(savedWarehouse);
    }

    @Transactional
    public WarehouseDto update(WarehouseDto warehouseDto) {
        validateWarehouseId(warehouseDto.getId());
        Warehouse warehouse = warehouseMapper.toEntity(warehouseDto);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return warehouseMapper.toDto(savedWarehouse);
    }

    @Transactional
    public void deleteById(UUID id) {
        warehouseRepository.deleteById(id);
    }

    private void validateWarehouseId(UUID id) {
        warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }
}
