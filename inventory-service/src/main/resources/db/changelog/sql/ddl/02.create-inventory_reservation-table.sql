CREATE TABLE IF NOT EXISTS inventory_reservation (
     id             UUID                        PRIMARY KEY DEFAULT gen_random_uuid(),
     order_id       UUID                        NOT NULL,
     product_id     UUID                        NOT NULL,
     warehouse_id   UUID                        NOT NULL,
     quantity       INT                         NOT NULL CHECK (quantity > 0),
     status         VARCHAR(50)                 NOT NULL,
     created_at     TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT NOW(),
     updated_at     TIMESTAMP WITH TIME ZONE    DEFAULT NOW(),

     UNIQUE (order_id, product_id, warehouse_id)
);
