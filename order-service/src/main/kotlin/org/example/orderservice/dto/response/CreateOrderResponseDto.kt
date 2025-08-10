package org.example.orderservice.dto.response

import org.example.orderservice.dto.OrderItemDto
import org.example.orderservice.entity.Order
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class CreateOrderResponseDto(
    val id: UUID,
    val userId: UUID,
    val status: Order.OrderStatus,
    val totalPrice: BigDecimal,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime?,
    var orderItems: List<OrderItemDto>
) {

}