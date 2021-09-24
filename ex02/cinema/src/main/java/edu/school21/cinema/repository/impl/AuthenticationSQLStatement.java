package edu.school21.cinema.repository.impl;

public enum AuthenticationSQLStatement {
    INSERT("INSERT INTO signIn (date, ip, user_id) VALUES (?, ?, ?)"),
    SELECT("SELECT ip, date FROM signIn WHERE signIn.user_id = ?");

    String QUERY;

    AuthenticationSQLStatement(String QUERY) {
        this.QUERY = QUERY;
    }
}
