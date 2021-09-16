package edu.school21.cinema.config;

import edu.school21.cinema.repository.UserRepository;
import edu.school21.cinema.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/jconcentdb");
        driverManagerDataSource.setUsername("jconcent");
        driverManagerDataSource.setPassword("1234567890");

        return driverManagerDataSource;
    }

    @Bean
    public UserRepository userRepositoryImpl(DataSource dataSource) {
        return new UserRepositoryImpl(dataSource);
    }
}
