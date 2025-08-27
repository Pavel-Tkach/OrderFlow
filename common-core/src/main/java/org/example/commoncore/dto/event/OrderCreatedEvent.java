package org.example.commoncore.dto.event;

import lombok.*;
import org.example.commoncore.dto.OrderItemsDetailDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent extends OrderEvent {

    private UUID orderId;

    private List<OrderItemsDetailDto> orderItemsDetailDto;
}
