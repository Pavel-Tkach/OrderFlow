CREATE TABLE IF NOT EXISTS order_outbox (
    id              UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    event_type      VARCHAR     NOT NULL,
    order_id        UUID        NOT NULL,
    payload         VARCHAR     NOT NULL,
    status          VARCHAR     CHECK ( status IN ('NEW', 'SENT', 'FAILED') ),
    created_at      TIMESTAMP   WITH TIME ZONE NOT NULL,
    sent_at         TIMESTAMP   WITH TIME ZONE
)
