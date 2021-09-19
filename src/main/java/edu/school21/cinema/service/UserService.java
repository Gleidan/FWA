package edu.school21.cinema.service;

import edu.school21.cinema.model.User;
import edu.school21.cinema.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        if (userRepository.getUserByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            System.out.println("email exists");
        }
    }

    public User getUserByEmail(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
