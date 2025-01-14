package com.epicode.gestione_viaggi.auth.requests_and_responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;

}
