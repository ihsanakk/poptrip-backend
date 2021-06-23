package com.example.tripadvisorservice.service;

import com.example.tripadvisorservice.controller.dto.RegisterRequest;
import com.example.tripadvisorservice.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUserName(String username);

    User registerUser(RegisterRequest registerRequest, String encodedPassword);

    boolean isUsernameExist(String username);

    boolean isEmailExist(String email);
}
