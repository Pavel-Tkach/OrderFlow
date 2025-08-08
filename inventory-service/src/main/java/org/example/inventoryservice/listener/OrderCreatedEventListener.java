package org.example.inventoryservice.listener;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.inventoryservice.dto.event.InventoryReservedEvent;
import org.example.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventListener {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order.created")
    public void processOrderCreatedEvent(ConsumerRecord<String, InventoryReservedEvent> record) {
        inventoryService.reserveInventory(record.value());
    }
}
