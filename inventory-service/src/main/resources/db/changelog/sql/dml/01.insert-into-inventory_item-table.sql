-- Sample inventory items with random UUIDs and realistic quantities
INSERT INTO inventory_item (product_id, warehouse_id, total_quantity, reserved_quantity)
VALUES
    -- Electronics
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'b3ba8a7d-7c67-4c5b-b12a-6a0c7d1e8f3d', 150, 25),
    ('550e8400-e29b-41d4-a716-446655440000', 'b3ba8a7d-7c67-4c5b-b12a-6a0c7d1e8f3d', 75, 10),

    -- Clothing
    ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '9f1a0b1d-8e3f-4a7d-bb2e-5c6f9d2e8f7c', 200, 50),
    ('6ba7b811-9dad-11d1-80b4-00c04fd430c9', '9f1a0b1d-8e3f-4a7d-bb2e-5c6f9d2e8f7c', 180, 30),

    -- Home Goods
    ('6ba7b812-9dad-11d1-80b4-00c04fd430c1', '2c9f1a0b-3d8e-4f7a-8d2e-5c6f9d2e8f7c', 90, 5),
    ('6ba7b813-9dad-11d1-80b4-00c04fd430c2', '2c9f1a0b-3d8e-4f7a-8d2e-5c6f9d2e8f7c', 120, 15),

    -- Books
    ('6ba7b814-9dad-11d1-80b4-00c04fd430c3', '1d2c9f1a-0b3d-8e4f-7a8d-2e5c6f9d2e8f', 300, 40),
    ('6ba7b815-9dad-11d1-80b4-00c04fd430c4', '1d2c9f1a-0b3d-8e4f-7a8d-2e5c6f9d2e8f', 250, 35),

    ('3fa85f64-5717-4562-b3fc-2c963f66afa6', '3fa85f64-5717-4562-b3fc-2c963f66afa6', 100, 20);
