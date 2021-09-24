package edu.school21.cinema.repository.impl;

import edu.school21.cinema.model.AuthenticationData;
import edu.school21.cinema.repository.AuthenticationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<AuthenticationData> dataRowMapper;

    public AuthenticationRepositoryImpl(DataSource dataSource, RowMapper<AuthenticationData> dataRowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataRowMapper = dataRowMapper;
    }

    @Override
    public int save(String ip, int userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(AuthenticationSQLStatement.INSERT.QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, ip);
            ps.setInt(3, userId);
            return ps;
        }, keyHolder);

        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<AuthenticationData> getAuthDataByUserId(int id) {
        return jdbcTemplate.query(AuthenticationSQLStatement.SELECT.QUERY, dataRowMapper, id);
    }
}
