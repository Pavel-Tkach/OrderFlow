CREATE TABLE IF NOT EXISTS inventory_item (
     id                 UUID                        PRIMARY KEY DEFAULT gen_random_uuid(),
     product_id         UUID                        NOT NULL,
     warehouse_id       UUID                        NOT NULL,
     total_quantity     INT                         NOT NULL CHECK (total_quantity >= 0),
     reserved_quantity  INT                         NOT NULL DEFAULT 0 CHECK (reserved_quantity >= 0),
     updated_at         TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT NOW(),

    UNIQUE (product_id, warehouse_id)
);
