package edu.school21.cinema.repository;

import edu.school21.cinema.model.User;

import java.util.List;

public interface UserRepository {
    Long save(User user);
    User getUserByEmail(String email);
}
