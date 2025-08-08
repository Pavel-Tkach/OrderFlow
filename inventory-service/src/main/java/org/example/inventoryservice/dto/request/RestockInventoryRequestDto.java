package org.example.inventoryservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestockInventoryRequestDto {

    private UUID productId;

    private UUID warehouseId;

    private Integer quantity;
}
