package org.example.commoncore.dto.event;

import lombok.*;
import org.example.commoncore.util.OrderStatus;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusChangedEvent extends OrderEvent {

    private UUID orderId;

    private OrderStatus status;
}
