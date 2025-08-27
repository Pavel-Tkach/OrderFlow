package org.example.commoncore.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDetailDto {

    private UUID productId;

    private UUID warehouseId;

    private Integer quantity;
}
