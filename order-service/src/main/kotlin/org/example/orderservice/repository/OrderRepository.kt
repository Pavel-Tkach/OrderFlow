package org.example.orderservice.repository

import org.example.orderservice.entity.Order
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderRepository: CoroutineCrudRepository<Order, UUID> {

}