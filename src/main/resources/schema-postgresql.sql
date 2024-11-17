------ MOVIE ------
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

------ END MOVIE ------
------ COMMENT ------
CREATE TABLE
  IF NOT EXISTS comment (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(50) DEFAULT '',
    content TEXT DEFAULT '',
    rating INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );

CREATE INDEX IF NOT EXISTS idx_created_at ON comment (created_at DESC);

-- CREATE TABLE
--   IF NOT EXISTS book_comment (
--     id VARCHAR(36) PRIMARY KEY,
--     comment_id VARCHAR(36) NOT NULL UNIQUE,
--     book_id VARCHAR(36) NOT NULL,
--     CONSTRAINT fk_comment FOREIGN KEY (comment_id) REFERENCES comment(id) ON DELETE CASCADE,
--     CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
--   );
CREATE TABLE
  IF NOT EXISTS movie_comment (
    id VARCHAR(36) PRIMARY KEY,
    comment_id VARCHAR(36) NOT NULL UNIQUE,
    movie_id VARCHAR(36) NOT NULL,
    CONSTRAINT fk_comment FOREIGN KEY (comment_id) REFERENCES comment (id) ON DELETE CASCADE,
    CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE
  );

------ END COMMENT ------