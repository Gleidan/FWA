package edu.school21.cinema.service;

import edu.school21.cinema.exception.IncorrectPasswordException;
import edu.school21.cinema.exception.UserAlreadyExistsException;
import edu.school21.cinema.exception.UserNotFoundException;
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

    public int save(User user) throws UserAlreadyExistsException {
        if (userRepository.getUserByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("Email already exists");
        }
    }

    public User getUserByEmail(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        } else {
            return user;
        }
    }
}
