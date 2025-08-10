package org.example.orderservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table(name = "order_item")
data class OrderItem(
    @Id
    val id: UUID?,

    @Column(value = "order_id")
    val orderId: UUID,

    @Column(value = "product_id")
    val productId: UUID,

    @Column(value = "warehouse_id")
    val warehouseId: UUID,

    @Column(value = "quantity")
    val quantity: Int,

    @Column(value = "unit_price")
    val unitPrice: BigDecimal,
) {

}