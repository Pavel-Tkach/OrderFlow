package org.example.productservice.repository;

import org.example.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = """
        WITH RECURSIVE categories AS (
            SELECT c.id, c.name, c.parent_id, c.created_at
            FROM category c
            WHERE c.id = :id
        
            UNION ALL
        
            SELECT c.id, c.name, c.parent_id, c.created_at
            FROM category c
            JOIN categories cat ON c.parent_id = cat.id
        )
        SELECT * FROM categories
        """, nativeQuery = true)
    List<Category> findAllWithRecursive(@Param("id") UUID id);

    Category findByName(String name);
}
