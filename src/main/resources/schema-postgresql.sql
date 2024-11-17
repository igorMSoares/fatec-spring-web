CREATE TABLE
  IF NOT EXISTS movie (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(50) DEFAULT '',
    genre VARCHAR(50) DEFAULT '',
    director VARCHAR(50) DEFAULT '',
    casting VARCHAR(100) DEFAULT '',
    synopsis TEXT DEFAULT '',
    imdb VARCHAR(10) DEFAULT '',
    year INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );

CREATE INDEX IF NOT EXISTS idx_timestamps ON movie (updated_at DESC, created_at DESC);