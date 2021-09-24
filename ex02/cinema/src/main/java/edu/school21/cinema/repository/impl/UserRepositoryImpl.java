package edu.school21.cinema.repository.impl;

import edu.school21.cinema.model.User;
import edu.school21.cinema.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper;

    public UserRepositoryImpl(DataSource dataSource, RowMapper<User> userRowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Integer save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(UserSQLStatement.INSERT.QUERY,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            return statement;
        }, keyHolder);
        return (Integer) keyHolder.getKeys().get("user_id");
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> users = jdbcTemplate.query(UserSQLStatement.SELECT.QUERY, userRowMapper, email);
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
