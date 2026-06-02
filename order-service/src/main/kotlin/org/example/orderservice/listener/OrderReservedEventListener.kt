package org.example.orderservice.listener

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.example.commoncore.dto.event.OrderStatusChangedEvent
import org.example.orderservice.service.OrderService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderReservedEventListener(
    private val orderService: OrderService,
) {

    @KafkaListener(topics = ["order.reserved"], groupId = "order-2")
    suspend fun processOrderReservedEvent(record: ConsumerRecord<String, OrderStatusChangedEvent>) {
        orderService.updateOrderStatus(record.value())
    }
}