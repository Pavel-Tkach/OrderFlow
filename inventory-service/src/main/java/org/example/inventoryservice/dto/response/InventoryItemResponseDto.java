package org.example.inventoryservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemResponseDto {

    private UUID productId;

    private UUID warehouseId;

    private Integer totalQuantity;

    private Integer reservedQuantity;

    private OffsetDateTime updatedAt;
}
