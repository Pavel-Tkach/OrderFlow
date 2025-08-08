CREATE TABLE IF NOT EXISTS category (
    id          UUID                     PRIMARY KEY,
    name        VARCHAR(30)              NOT NULL,
    parent_id   UUID,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

    FOREIGN KEY (parent_id) REFERENCES category(id)
);
