package com.epicode.gestione_viaggi.auth.requests_and_responses;

import lombok.Data;


@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
}
