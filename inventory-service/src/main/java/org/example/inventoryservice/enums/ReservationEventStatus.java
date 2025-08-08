package org.example.inventoryservice.enums;

import lombok.Getter;

@Getter
public enum ReservationEventStatus {

    INVENTORY_RESERVED("inventory.reserved"),
    INVENTORY_RELEASED("inventory.released"),
    INVENTORY_DEDUCTED("inventory.deducted"),
    INVENTORY_RESTOCKED("inventory.restocked");

    private final String status;

    ReservationEventStatus(String status) {
        this.status = status;

    }
}
