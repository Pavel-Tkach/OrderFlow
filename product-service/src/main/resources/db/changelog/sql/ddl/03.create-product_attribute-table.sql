CREATE TABLE IF NOT EXISTS product_attribute (
      id UUID PRIMARY KEY,
      product_id    UUID        NOT NULL,
      name          VARCHAR(50) NOT NULL,
      value         VARCHAR(50) NOT NULL,

      FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);
