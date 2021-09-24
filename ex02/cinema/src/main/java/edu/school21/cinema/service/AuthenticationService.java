package edu.school21.cinema.service;

import edu.school21.cinema.model.AuthenticationData;
import edu.school21.cinema.repository.AuthenticationRepository;

import java.util.List;

public class AuthenticationService {

    private final AuthenticationRepository repository;

    public AuthenticationService(AuthenticationRepository repository) {
        this.repository = repository;
    }

    public int save(String ip, int userId) {
        return repository.save(ip, userId);
    }

    public List<AuthenticationData> getAuthenticationDataByUserId(int id) {
        return repository.getAuthDataByUserId(id);
    }
}
