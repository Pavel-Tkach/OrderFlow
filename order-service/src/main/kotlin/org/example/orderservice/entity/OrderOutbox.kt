package org.example.orderservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime
import java.util.UUID

@Table(name = "order_outbox")
data class OrderOutbox(
    @Id
    val id: UUID?,
    @Column(value = "event_type")
    val eventType: String,
    @Column(value = "order_id")
    val orderId: UUID,
    @Column(value = "payload")
    val payload: String,
    @Column(value = "status")
    val status: OutboxStatus,
    @Column(value = "created_at")
    val createdAt: OffsetDateTime,
    @Column(value = "sent_at")
    val sentAt: OffsetDateTime?,
) {

    constructor(
        eventType: String,
        orderId: UUID,
        payload: String,
        status: OutboxStatus,
    ) : this(
        id = null,
        eventType = eventType,
        orderId = orderId,
        payload = payload,
        status = status,
        createdAt = OffsetDateTime.now(),
        sentAt = null,
    )

    enum class OutboxStatus {
        NEW,
        SENT,
        FAILED
    }
}
