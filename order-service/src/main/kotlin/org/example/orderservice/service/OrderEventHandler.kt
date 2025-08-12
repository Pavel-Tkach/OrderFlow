package org.example.orderservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.orderservice.dto.OrderItemsDetailDto
import org.example.orderservice.entity.OrderOutbox
import org.example.orderservice.producer.OrderEventProducer
import org.example.orderservice.repository.OrderOutboxRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class OrderEventHandler(
    private val orderEventProducer: OrderEventProducer,
    private val orderOutboxRepository: OrderOutboxRepository,
) {

    @Scheduled(cron = "0 * * * * *")
    suspend fun getOutboxDataFromDatabaseAndPushToKafka() {
        val outboxes = orderOutboxRepository.findAllWithMaxSize(25)
        outboxes.map { orderOutbox ->
            run {
                val orderId = orderOutbox.orderId
                val payload = orderOutbox.payload
                val objectMapper = ObjectMapper()
                val orderItemsDetailDto = listOf(objectMapper.readValue(payload, OrderItemsDetailDto::class.java))
                orderEventProducer.publishOrderCreatedEvent(orderId, orderItemsDetailDto)
                orderOutbox.sentAt = OffsetDateTime.now()
                orderOutbox.status = OrderOutbox.OutboxStatus.SENT
            }
            orderOutboxRepository.save(orderOutbox)
        }
    }
}
