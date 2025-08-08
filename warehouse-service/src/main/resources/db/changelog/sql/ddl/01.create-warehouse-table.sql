CREATE TABLE IF NOT EXISTS warehouse (
     id             UUID                        PRIMARY KEY,
     name           TEXT                        NOT NULL,
     location       TEXT                        NOT NULL,
     latitude       DOUBLE PRECISION            NOT NULL,
     longitude      DOUBLE PRECISION            NOT NULL,
     is_active      BOOLEAN                     NOT NULL,
     created_at     TIMESTAMP WITH TIME ZONE    DEFAULT NOW()
);
