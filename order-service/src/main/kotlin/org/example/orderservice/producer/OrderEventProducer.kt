package org.example.orderservice.producer

import org.example.orderservice.dto.OrderItemsDetailDto
import org.example.orderservice.dto.event.OrderCreatedEvent
import org.example.orderservice.dto.event.OrderEvent
import org.example.orderservice.util.Constants.ORDER_CREATED
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OrderEventProducer(
    private val orderCreatedKafkaTemplate: KafkaTemplate<String, OrderEvent>
) {

    fun publishOrderCreatedEvent(orderId: UUID, orderItemsDetailDtos: List<OrderItemsDetailDto>) {
        val orderCreatedEvent = OrderCreatedEvent(orderId, orderItemsDetailDtos)
        orderCreatedKafkaTemplate.send(ORDER_CREATED, orderCreatedEvent)
    }
}
