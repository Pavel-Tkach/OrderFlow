CREATE TABLE IF NOT EXISTS product (
     id             UUID                        PRIMARY KEY,
     sku            VARCHAR(30)                 UNIQUE NOT NULL,
     name           VARCHAR(50)                 NOT NULL,
     description    VARCHAR(150),
     price          NUMERIC(12, 2)              NOT NULL CHECK (price >= 0),
     category_id    UUID,
     is_active      BOOLEAN,
     created_at     TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT NOW(),
     updated_at     TIMESTAMP WITH TIME ZONE             DEFAULT NOW(),

     FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);
