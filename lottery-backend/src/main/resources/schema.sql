CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS rounds (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  started_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  closed_at TIMESTAMP WITH TIME ZONE,
  numbers integer[] DEFAULT NULL,
  active boolean DEFAULT true
);

CREATE TABLE IF NOT EXISTS tickets (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  round_id UUID REFERENCES rounds(id) ON DELETE SET NULL,
  user_id VARCHAR(20) NOT NULL,
  numbers integer[] NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_tickets_round ON tickets(round_id);