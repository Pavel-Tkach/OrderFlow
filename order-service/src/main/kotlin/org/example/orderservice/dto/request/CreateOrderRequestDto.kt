package org.example.orderservice.dto.request

import org.example.orderservice.dto.OrderItemDto

data class CreateOrderRequestDto(
    val orderItems: List<OrderItemDto>
) {

}