package org.example.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.inventoryservice.dto.event.OrderCreatedEvent;
import org.example.inventoryservice.dto.request.DeductInventoryRequestDto;
import org.example.inventoryservice.dto.request.RealiseInventoryRequestDto;
import org.example.inventoryservice.dto.request.RestockInventoryRequestDto;
import org.example.inventoryservice.entity.InventoryItem;
import org.example.inventoryservice.entity.InventoryReservation;
import org.example.inventoryservice.enums.ReservationStatus;
import org.example.inventoryservice.repository.InventoryItemRepository;
import org.example.inventoryservice.repository.InventoryReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryReservationRepository reservationRepository;

    @Transactional
    public void reserveInventory(OrderCreatedEvent orderCreatedEvent) {
        UUID orderId = orderCreatedEvent.getOrderId();
        List<InventoryReservation> reservations = orderCreatedEvent.getOrderItemsDetailDto()
                .stream().map(orderItemsDetailDto -> {
                    UUID productId = orderItemsDetailDto.getProductId();
                    UUID warehouseId = orderItemsDetailDto.getWarehouseId();
                    Integer quantity = orderItemsDetailDto.getQuantity();
                    InventoryItem inventoryItem = inventoryItemRepository.findByProductIdAndWarehouseId(productId, warehouseId);
                    checkAvailableQuantityForReservation(quantity, inventoryItem);
                    inventoryItem.setReservedQuantity(quantity + inventoryItem.getReservedQuantity());
                    inventoryItemRepository.save(inventoryItem);

                    return InventoryReservation.builder()
                            .status(ReservationStatus.RESERVED)
                            .orderId(orderId)
                            .productId(productId)
                            .warehouseId(warehouseId)
                            .quantity(quantity)
                            .build();
                }).toList();
        reservationRepository.saveAll(reservations);
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
