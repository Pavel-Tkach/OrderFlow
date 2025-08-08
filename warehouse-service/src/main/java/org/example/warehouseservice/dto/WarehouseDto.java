package org.example.warehouseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WarehouseDto {

    private UUID id;

    private String name;

    private String location;

    private Double latitude;

    private Double longitude;

    private Boolean isActive;

    private OffsetDateTime createdAt;
}
