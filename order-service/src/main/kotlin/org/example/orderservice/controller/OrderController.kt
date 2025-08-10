package org.example.orderservice.controller

import org.example.orderservice.dto.request.CreateOrderRequestDto
import org.example.orderservice.dto.response.CreateOrderResponseDto
import org.example.orderservice.service.OrderService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class OrderController(
    private val orderService: OrderService,
) {

    @PostMapping("/orders")
    suspend fun createOrder(@RequestBody createOrderRequestDto: CreateOrderRequestDto): CreateOrderResponseDto {
        return orderService.createOrder(createOrderRequestDto)
    }

    @DeleteMapping("/orders/{orderId}")
    suspend fun deleteOrder(@PathVariable orderId: UUID) {
        orderService.deleteOrderById(orderId)
    }
}