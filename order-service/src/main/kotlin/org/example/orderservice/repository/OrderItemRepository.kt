package org.example.orderservice.repository

import org.example.orderservice.entity.OrderItem
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderItemRepository: R2dbcRepository<OrderItem, UUID> {

}