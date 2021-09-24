package edu.school21.cinema.repository;

import edu.school21.cinema.model.AuthenticationData;

import java.util.List;

public interface AuthenticationRepository {

    int save(String ip, int userId);
    List<AuthenticationData> getAuthDataByUserId(int id);
}
