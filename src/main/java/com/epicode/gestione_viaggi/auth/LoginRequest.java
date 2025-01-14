package com.epicode.gestione_viaggi.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
