package org.example.inventoryservice.producer;

import lombok.RequiredArgsConstructor;
import org.example.commoncore.dto.event.OrderEvent;
import org.example.commoncore.dto.event.OrderStatusChangedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusEventProducer {

    private final KafkaTemplate<String, Object> orderStatusChangedEventKafkaTemplate;

    public void publishOrderStatusChangedEvent(String topicName, OrderStatusChangedEvent orderStatusChangedEvent) {
        orderStatusChangedEventKafkaTemplate.send(topicName, orderStatusChangedEvent);
    }
}
