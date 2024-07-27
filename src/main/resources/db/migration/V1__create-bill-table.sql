CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE bill (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    description VARCHAR(250) NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    due_date TIMESTAMP NOT NULL,
    payment_date TIMESTAMP,
    status INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);