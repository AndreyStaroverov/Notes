DROP TABLE IF EXISTS users, notes;

CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS notes (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  text VARCHAR(255) NOT NULL,
  pinned BOOLEAN DEFAULT FALSE,
  owner_id BIGINT NOT NULL,
  createdate TIMESTAMP NOT NULL,
  CONSTRAINT pk_note PRIMARY KEY (id),
  CONSTRAINT fk_owner_id FOREIGN KEY(owner_id) REFERENCES users (id)
);

-- Создание пользователя
INSERT INTO users (username, email, password,created_at)
VALUES ('Backend Developer', '123@example.com', 'password123', NOW());
INSERT INTO users (username, email, password,created_at)
VALUES ('Backend Developer2', '1234@example.com', 'password1234', NOW());

-- Создание заметки
INSERT INTO notes (text, owner_id, createDate)
VALUES ('Это содержимое моей первой заметки.', 1, NOW());

--CREATE INDEX idx_notes_user_id ON notes (owner_id);