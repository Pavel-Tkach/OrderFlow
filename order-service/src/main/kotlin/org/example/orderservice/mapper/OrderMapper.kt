package org.example.orderservice.mapper

import org.example.orderservice.dto.response.CreateOrderResponseDto
import org.example.orderservice.entity.Order
import org.example.orderservice.entity.OrderItem
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING, uses = [ OrderItemMapper::class ])
interface OrderMapper {

    @Mapping(target = "orderItems", source = "orderItems")
    fun toCreateOrderResponseDto(order: Order, orderItems: List<OrderItem>): CreateOrderResponseDto
}
