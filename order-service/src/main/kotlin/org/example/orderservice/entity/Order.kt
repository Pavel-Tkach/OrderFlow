package org.example.orderservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Table(name = "\"order\"")
data class Order(

    @Id
    val id: UUID?,

    @Column(value = "user_id")
    val userId: UUID,

    @Column(value = "status")
    var status: OrderStatus? = OrderStatus.NEW,

    @Column(value = "total_price")
    var totalPrice: BigDecimal,

    @Column(value = "created_at")
    val createdAt: OffsetDateTime,

    @Column(value = "updated_at")
    val updatedAt: OffsetDateTime?,
) {

    constructor(
        userId: UUID,
        totalPrice: BigDecimal,
        createdAt: OffsetDateTime = OffsetDateTime.now()
    ) : this(
        id = null,
        userId = userId,
        status = OrderStatus.NEW,
        totalPrice = totalPrice,
        createdAt = createdAt,
        updatedAt = null
    )

    enum class OrderStatus {
        NEW,
        RESERVED,
        PAID,
        SHIPPED,
        COMPLETED,
        CANCELLED,
    }

}
