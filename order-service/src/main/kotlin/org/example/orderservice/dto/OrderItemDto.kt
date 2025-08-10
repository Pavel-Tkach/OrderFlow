package org.example.orderservice.dto

import java.math.BigDecimal
import java.util.*

data class OrderItemDto(
    val id: UUID?,
    var orderId: UUID?,
    val productId: UUID,
    val warehouseId: UUID,
    val quantity: Int,
    val unitPrice: BigDecimal,
) {

}