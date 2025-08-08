package org.example.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.inventoryservice.dto.event.InventoryReservedEvent;
import org.example.inventoryservice.dto.request.DeductInventoryRequestDto;
import org.example.inventoryservice.dto.request.RealiseInventoryRequestDto;
import org.example.inventoryservice.dto.request.RestockInventoryRequestDto;
import org.example.inventoryservice.dto.response.InventoryItemResponseDto;
import org.example.inventoryservice.dto.request.ReserveInventoryRequestDto;
import org.example.inventoryservice.entity.InventoryItem;
import org.example.inventoryservice.mapper.InventoryItemMapper;
import org.example.inventoryservice.repository.InventoryItemRepository;
import org.example.inventoryservice.repository.InventoryReservationRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.example.inventoryservice.enums.ReservationEventStatus.INVENTORY_RESERVED;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemMapper inventoryItemMapper;
    private final KafkaTemplate<String, InventoryReservedEvent> kafkaTemplate;
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public InventoryItemResponseDto getInventory(UUID productId, UUID warehouseId) {
        InventoryItem inventoryItem = inventoryItemRepository.findByProductIdAndWarehouseId(productId, warehouseId);

        return inventoryItemMapper.toDto(inventoryItem);
    }

    @Transactional
    public void reserveInventory(ReserveInventoryRequestDto reserveInventoryRequestDto) {
        UUID productId = reserveInventoryRequestDto.getProductId();
        UUID warehouseId = reserveInventoryRequestDto.getWarehouseId();
        Integer quantity = reserveInventoryRequestDto.getQuantity();
        InventoryItem inventoryItem = inventoryItemRepository.findByProductIdAndWarehouseId(productId, warehouseId);
        checkAvailableQuantityForReservation(quantity, inventoryItem);
        inventoryItem.setReservedQuantity(quantity + inventoryItem.getReservedQuantity());
        inventoryItemRepository.save(inventoryItem);
        InventoryReservedEvent inventoryReservedEvent = InventoryReservedEvent.builder()
                .eventStatus(INVENTORY_RESERVED)
                .orderId(null)
                .productId(productId)
                .warehouseId(warehouseId)
                .quantity()
                .eventTime(OffsetDateTime.now())
                .build();
    }

    public void realiseInventory(RealiseInventoryRequestDto realiseInventoryRequestDto) {

    }

    public void deductInventory(DeductInventoryRequestDto deductInventoryRequestDto) {

    }

    public void restockInventory(RestockInventoryRequestDto restockInventoryRequestDto) {

    }

    private void checkAvailableQuantityForReservation(Integer quantity, InventoryItem inventoryItem) {
        Integer totalQuantity = inventoryItem.getTotalQuantity();
        Integer reservedQuantity = inventoryItem.getReservedQuantity();
        if (reservedQuantity + quantity > totalQuantity) {
            throw new RuntimeException("Reserved quantity exceeds total quantity");
        }
    }
}
