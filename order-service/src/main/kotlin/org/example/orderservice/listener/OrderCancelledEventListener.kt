package org.example.orderservice.listener

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.example.commoncore.dto.event.OrderStatusChangedEvent
import org.example.orderservice.service.OrderService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderCancelledEventListener(
    private val orderService: OrderService,
) {

    @KafkaListener(topics = ["order.cancelled"], groupId = "order-2")
    suspend fun processOrderCancelledEvent(record: ConsumerRecord<String, OrderStatusChangedEvent>) {
        orderService.updateOrderStatus(record.value())
    }
}