package org.example.orderservice.repository

import org.example.orderservice.entity.OrderItem
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderItemRepository: CoroutineCrudRepository<OrderItem, UUID> {

}