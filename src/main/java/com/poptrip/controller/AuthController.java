package com.poptrip.controller;

import com.poptrip.controller.dto.LoginRequest;
import com.poptrip.controller.dto.LoginResponse;
import com.poptrip.controller.dto.MessageResponse;
import com.poptrip.controller.dto.RegisterRequest;
import com.poptrip.security.jwt.JwtUtils;
import com.poptrip.security.userdetails.UserDetailsImpl;
import com.poptrip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){

        if(!userService.findUserByUserName(loginRequest.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Username is not present"));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJWT(userDetails.getUsername());

        List<String> roles = userDetails.getAuthorities().stream().map(role->role.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok().body(new LoginResponse(userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),jwt,roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){

        if(userService.isUsernameExist(registerRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Username is already in use!"));
        }
        if(userService.isEmailExist(registerRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use"));
        }
        userService.registerUser(registerRequest, passwordEncoder.encode(registerRequest.getPassword()));

        return ResponseEntity.ok().body(new MessageResponse("User registered successfully"));

    }


}
