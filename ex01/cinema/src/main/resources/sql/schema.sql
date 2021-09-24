CREATE TABLE IF NOT EXISTS cinema_users
(
    user_id serial PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS signIn
(
    id serial PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    ip VARCHAR(64) NOT NULL,
    user_id INTEGER REFERENCES cinema_users (user_id)
);