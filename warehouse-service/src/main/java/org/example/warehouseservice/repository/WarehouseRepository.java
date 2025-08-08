package org.example.warehouseservice.repository;

import org.example.warehouseservice.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {

    @Query(value = "SELECT * FROM find_nearest_warehouse(:lat, :lon)", nativeQuery = true)
    Warehouse findNearestWarehouseByCoordinates(@Param("lat") Double lat, @Param("lon") Double lon);
}
