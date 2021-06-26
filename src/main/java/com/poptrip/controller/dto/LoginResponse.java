package com.poptrip.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {

    private int id;
    private String username;
    private String email;
    private String jwt;
    private List<String> roles;
}
