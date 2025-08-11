package org.example.orderservice.service

import org.example.orderservice.dto.request.CreateOrderRequestDto
import org.example.orderservice.dto.response.CreateOrderResponseDto
import org.example.orderservice.entity.Order
import org.example.orderservice.mapper.OrderItemMapper
import org.example.orderservice.mapper.OrderMapper
import org.example.orderservice.producer.OrderEventProducer
import org.example.orderservice.repository.OrderItemRepository
import org.example.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.UUID

@Service
class OrderService(
    private val orderMapper: OrderMapper,
    private val orderItemMapper: OrderItemMapper,
    private val orderRepository : OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val orderEventProducer: OrderEventProducer,
) {

    @Transactional
    suspend fun createOrder(createOrderRequestDto: CreateOrderRequestDto): CreateOrderResponseDto {
        val totalPrice = calculateTotalPrice(createOrderRequestDto)
        val order = Order(UUID.randomUUID(), totalPrice)
        val savedOrder = orderRepository.save(order)
        setOrderIdToOrderItems(createOrderRequestDto, savedOrder.id!!)
        val items = createOrderRequestDto.orderItems
            .map { orderItemMapper.toEntity(it) }
        val savedOrderItems = items.map { orderItemRepository.save(it) }
        // save event to OrderOutbox

        return orderMapper.toCreateOrderResponseDto(savedOrder, savedOrderItems)
    }

    @Transactional
    suspend fun deleteOrderById(orderId: UUID) {
        orderRepository.findById(orderId) ?: throw RuntimeException("Order with id $orderId not found")
        orderRepository.deleteById(orderId)
    }

    private fun calculateTotalPrice(createOrderRequestDto: CreateOrderRequestDto): BigDecimal {
        return createOrderRequestDto.orderItems
            .map { orderItem ->
                orderItem.unitPrice.multiply(BigDecimal(orderItem.quantity))
            }
            .fold(BigDecimal.ZERO) { acc, price ->
                acc.add(price)
            }
    }

    private fun setOrderIdToOrderItems(createOrderRequestDto: CreateOrderRequestDto, orderId: UUID) {
        createOrderRequestDto.orderItems.forEach { orderItem -> orderItem.orderId = orderId }
    }
}
