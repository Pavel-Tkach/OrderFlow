package org.example.orderservice.mapper

import org.example.orderservice.dto.OrderItemDto
import org.example.orderservice.entity.OrderItem
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
interface OrderItemMapper {

    fun toEntity(dto: OrderItemDto): OrderItem
}
