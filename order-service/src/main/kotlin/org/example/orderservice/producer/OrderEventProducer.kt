package org.example.orderservice.producer

import org.example.orderservice.dto.OrderItemDto
import org.example.orderservice.dto.OrderItemsDetailDto
import org.example.orderservice.dto.event.OrderCreatedEvent
import org.example.orderservice.dto.event.OrderEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OrderEventProducer(
    private val orderCreatedKafkaTemplate: KafkaTemplate<String, OrderEvent>
) {

    fun publishOrderCreatedEvent(orderId: UUID, orderItems: List<OrderItemDto>) {
        val orderItemsDetailDtos = orderItems.map { orderItemDto ->
            OrderItemsDetailDto(
                orderItemDto.productId,
                orderItemDto.warehouseId,
                orderItemDto.quantity
            )
        }
        val orderCreatedEvent = OrderCreatedEvent(orderId, orderItemsDetailDtos)
        orderCreatedKafkaTemplate.send("order.created", orderCreatedEvent)
    }
}
