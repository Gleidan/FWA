package edu.school21.cinema.repository.impl;

public enum UserSQLStatement {
    INSERT("INSERT INTO cinema_users (email, password, first_name, last_name) VALUES (?, ?, ?, ?)"),
    SELECT("SELECT * FROM cinema_users WHERE cinema_users.email = ?");

    String QUERY;

    UserSQLStatement(String QUERY) {
        this.QUERY = QUERY;
    }
}
