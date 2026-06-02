package org.example.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventoryservice.dto.request.RestockInventoryRequestDto;
import org.example.inventoryservice.dto.response.InventoryItemResponseDto;
import org.example.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/inventories")
    public InventoryItemResponseDto restockInventory(@RequestBody RestockInventoryRequestDto restockInventoryRequestDto) {
        return inventoryService.restockInventory(restockInventoryRequestDto);
    }
}
