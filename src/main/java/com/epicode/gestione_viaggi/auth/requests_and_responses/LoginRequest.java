package com.epicode.gestione_viaggi.auth.requests_and_responses;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
