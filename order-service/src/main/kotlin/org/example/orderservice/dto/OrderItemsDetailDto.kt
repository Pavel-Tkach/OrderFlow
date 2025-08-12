package org.example.orderservice.dto

import java.util.*

data class OrderItemsDetailDto(
    val productId: UUID?,
    val warehouseId: UUID?,
    val quantity: Int?,
) {

    constructor() : this(
        productId = null,
        warehouseId = null,
        quantity = null,
    )
}
