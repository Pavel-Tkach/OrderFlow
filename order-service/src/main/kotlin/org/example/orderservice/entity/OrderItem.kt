package org.example.orderservice.entity

import java.math.BigDecimal
import java.util.UUID

data class OrderItem(
    val id: UUID,
    val orderId: UUID,
    val productId: UUID,
    val warehouseId: UUID,
    val quantity: Int,
    val unitPrice: BigDecimal,
) {
}