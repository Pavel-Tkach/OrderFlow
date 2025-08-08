package org.example.orderservice.entity

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class Order(
    val id: UUID,
    val userId: UUID,
    var status: OrderStatus,
    var totalPrice: BigDecimal,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
) {
}