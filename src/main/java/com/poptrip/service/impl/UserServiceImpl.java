package com.poptrip.service.impl;

import com.poptrip.controller.dto.RegisterRequest;
import com.poptrip.entity.Role;
import com.poptrip.entity.User;
import com.poptrip.entity.enums.ERole;
import com.poptrip.repo.RoleRepository;
import com.poptrip.repo.UserRepository;
import com.poptrip.service.UserService;
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
