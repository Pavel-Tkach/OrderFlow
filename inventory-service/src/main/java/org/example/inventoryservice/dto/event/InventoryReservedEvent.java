package org.example.inventoryservice.dto.event;

import lombok.*;
import org.example.inventoryservice.enums.ReservationEventStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryReservedEvent {

    private ReservationEventStatus eventStatus;

    private UUID orderId;

    private UUID productId;

    private UUID warehouseId;

    private Integer quantity;

    private OffsetDateTime eventTime;
}
