package org.example.inventoryservice.repository;

import org.example.inventoryservice.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {

    InventoryItem findByProductIdAndWarehouseId(UUID productId, UUID warehouseId);
}
