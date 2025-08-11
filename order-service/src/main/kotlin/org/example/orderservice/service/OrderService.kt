package org.example.orderservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.orderservice.dto.OrderItemDto
import org.example.orderservice.dto.OrderItemsDetailDto
import org.example.orderservice.dto.request.CreateOrderRequestDto
import org.example.orderservice.dto.response.CreateOrderResponseDto
import org.example.orderservice.entity.Order
import org.example.orderservice.entity.OrderOutbox
import org.example.orderservice.mapper.OrderItemMapper
import org.example.orderservice.mapper.OrderMapper
import org.example.orderservice.repository.OrderItemRepository
import org.example.orderservice.repository.OrderOutboxRepository
import org.example.orderservice.repository.OrderRepository
import org.example.orderservice.util.Constants.ORDER_CREATED
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
    private val orderOutboxRepository: OrderOutboxRepository,
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
        val orderOutbox = buildOrderOutbox(savedOrder.id, createOrderRequestDto)
        orderOutboxRepository.save(orderOutbox)

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

    private fun buildOrderOutbox(orderId: UUID, createOrderRequestDto: CreateOrderRequestDto): OrderOutbox {
        return OrderOutbox(
            ORDER_CREATED,
            orderId,
            getJsonFor(convertToOrderItemsDetailDtos(createOrderRequestDto.orderItems)),
            OrderOutbox.OutboxStatus.NEW
        )
    }

    private fun getJsonFor(forSerialized: Any): String {
        val objectMapper = ObjectMapper()

        return objectMapper.writeValueAsString(forSerialized)
    }

    private fun convertToOrderItemsDetailDtos(orderItems: List<OrderItemDto>): List<OrderItemsDetailDto> {
        return orderItems.map { orderItemDto ->
            OrderItemsDetailDto(
                orderItemDto.productId,
                orderItemDto.warehouseId,
                orderItemDto.quantity
            )
        }
    }
}
