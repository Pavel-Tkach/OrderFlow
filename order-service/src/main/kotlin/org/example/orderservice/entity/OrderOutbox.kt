package org.example.orderservice.entity

import java.util.UUID

data class OrderOutbox(
    val id: UUID,
    val eventType: String,
    
) {
}