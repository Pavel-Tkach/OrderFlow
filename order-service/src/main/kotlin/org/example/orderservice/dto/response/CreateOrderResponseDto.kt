package org.example.orderservice.dto.response

import org.example.commoncore.util.OrderStatus
import org.example.orderservice.dto.OrderItemDto
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class CreateOrderResponseDto(
    val id: UUID,
    val userId: UUID,
    val status: OrderStatus,
    val totalPrice: BigDecimal,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime?,
    var orderItems: List<OrderItemDto>
) {

}