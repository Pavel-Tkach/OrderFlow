CREATE TABLE IF NOT EXISTS "order" (
    id              UUID                        PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id         UUID                        NOT NULL,
    status          VARCHAR                     CHECK ( status IN ('NEW', 'RESERVED', 'PAID', 'SHIPPED', 'COMPLETED', 'CANCELLED') ),
    total_price     DECIMAL                     NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE    NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE
);
