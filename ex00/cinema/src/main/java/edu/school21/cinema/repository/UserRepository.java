package edu.school21.cinema.repository;

import edu.school21.cinema.model.User;

public interface UserRepository {
    Integer save(User user);
    User getUserByEmail(String email);
}
