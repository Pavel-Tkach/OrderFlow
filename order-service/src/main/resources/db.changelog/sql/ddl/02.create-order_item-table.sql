CREATE TABLE IF NOT EXISTS order_item (
    id              UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id        UUID        NOT NULL,
    product_id      UUID        NOT NULL,
    warehouse_id    UUID        NOT NULL,
    quantity        INT         NOT NULL,
    unit_price      DECIMAL     NOT NULL
);
