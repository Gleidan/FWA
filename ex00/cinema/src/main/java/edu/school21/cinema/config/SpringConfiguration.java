package edu.school21.cinema.config;

import edu.school21.cinema.model.AuthenticationData;
import edu.school21.cinema.model.User;
import edu.school21.cinema.repository.AuthenticationRepository;
import edu.school21.cinema.repository.UserRepository;
import edu.school21.cinema.repository.impl.AuthenticationRepositoryImpl;
import edu.school21.cinema.repository.impl.UserRepositoryImpl;
import edu.school21.cinema.service.AuthenticationService;
import edu.school21.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@PropertySource("../application.properties")
public class SpringConfiguration {

    @Value("${datasource.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String userName;
    @Value("${datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setPassword(password);

        return driverManagerDataSource;
    }

    @Bean
    public UserRepository userRepository(DataSource dataSource, RowMapper<User> userRowMapper) {
        return new UserRepositoryImpl(dataSource, userRowMapper);
    }

    @Bean
    public RowMapper<User> userRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                return new User(id, email, password, firstName, lastName);
            }
        };
    }

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserService(userRepository, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationRepository authenticationRepository(DataSource dataSource, RowMapper<AuthenticationData> dataRowMapper) {
        return new AuthenticationRepositoryImpl(dataSource, dataRowMapper);
    }

    @Bean
    public RowMapper<AuthenticationData> dataRowMapper() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new RowMapper<AuthenticationData>() {
            @Override
            public AuthenticationData mapRow(ResultSet resultSet, int i) throws SQLException {
                String ip = resultSet.getString("ip");
                LocalDateTime date = LocalDateTime.parse(formatter.format(resultSet.getTimestamp("date").toLocalDateTime()), formatter);
                return new AuthenticationData(ip, date);
            }
        };
    }

    @Bean
    public AuthenticationService authenticationService(AuthenticationRepository authenticationRepository) {
        return new AuthenticationService(authenticationRepository);
    }
}
