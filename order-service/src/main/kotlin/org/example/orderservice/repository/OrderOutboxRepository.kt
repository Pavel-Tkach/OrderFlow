package org.example.orderservice.repository

import org.example.orderservice.entity.OrderOutbox
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderOutboxRepository: CoroutineCrudRepository<OrderOutbox, UUID> {

}
