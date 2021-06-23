package com.example.tripadvisorservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @NotNull(message = "username must not be null")
    private String username;
    @NotNull(message = "password must not be null")
    private String password;
}
