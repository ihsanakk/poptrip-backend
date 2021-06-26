package com.poptrip.service;

import com.poptrip.controller.dto.RegisterRequest;
import com.poptrip.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUserName(String username);

    User registerUser(RegisterRequest registerRequest, String encodedPassword);

    boolean isUsernameExist(String username);

    boolean isEmailExist(String email);
}
