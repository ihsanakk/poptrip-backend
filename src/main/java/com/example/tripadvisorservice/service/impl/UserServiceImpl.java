package com.example.tripadvisorservice.service.impl;

import com.example.tripadvisorservice.controller.dto.RegisterRequest;
import com.example.tripadvisorservice.entity.Role;
import com.example.tripadvisorservice.entity.User;
import com.example.tripadvisorservice.entity.enums.ERole;
import com.example.tripadvisorservice.repo.RoleRepository;
import com.example.tripadvisorservice.repo.UserRepository;
import com.example.tripadvisorservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Optional<User> findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerUser(RegisterRequest registerRequest, String encodedPassword) {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());

        user.setPassword(encodedPassword);

        Set<Role> roleSet = new HashSet<>();
        Role role = roleRepository.findByRoleName(ERole.ROLE_USER);
        roleSet.add(role);

        user.setRoles(roleSet);

        userRepository.save(user);
        return user;

    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
