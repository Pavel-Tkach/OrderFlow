package org.example.warehouseservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.warehouseservice.dto.WarehouseDto;
import org.example.warehouseservice.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping("/warehouses")
    public List<WarehouseDto> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/warehouses/nearest")
    public WarehouseDto getNearestWarehouse(@RequestParam Double latitude, @RequestParam Double longitude) {
        return warehouseService.getNearestWarehouseByCoordinates(latitude, longitude);
    }

    @PostMapping("/warehouses")
    public WarehouseDto createWarehouse(@RequestBody WarehouseDto warehouseDto) {
        return warehouseService.create(warehouseDto);
    }

    @PutMapping("/warehouses")
    public WarehouseDto updateWarehouse(@RequestBody WarehouseDto warehouseDto) {
        return warehouseService.update(warehouseDto);
    }

    @DeleteMapping("/warehouses/{warehouseId}")
    public void deleteWarehouse(@PathVariable UUID warehouseId) {
        warehouseService.deleteById(warehouseId);
    }
}
