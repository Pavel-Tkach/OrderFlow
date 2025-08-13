package org.example.inventoryservice.listener;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.inventoryservice.dto.event.OrderCreatedEvent;
import org.example.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventListener {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order.created", groupId = "order-1")
    public void processOrderCreatedEvent(ConsumerRecord<String, OrderCreatedEvent> record) {
        inventoryService.reserveInventory(record.value());
    }
}
