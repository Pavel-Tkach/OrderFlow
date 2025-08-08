package org.example.inventoryservice.listener;

import lombok.RequiredArgsConstructor;
import org.example.inventoryservice.dto.event.InventoryReservedEvent;
import org.example.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventListener {

    private final KafkaTemplate<String, InventoryReservedEvent> kafkaTemplate;
    private final InventoryService inventoryService;

    @KafkaListener(topics = "order.created")
    public void processOrderCreatedEvent() {

    }
}
