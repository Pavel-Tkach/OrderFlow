package org.example.orderservice.dto.event

import org.example.orderservice.dto.OrderItemsDetailDto
import java.util.UUID

data class OrderCreatedEvent(
    val orderId: UUID,
    val orderItemsDetailDto: List<OrderItemsDetailDto>,
): OrderEvent() {

}
