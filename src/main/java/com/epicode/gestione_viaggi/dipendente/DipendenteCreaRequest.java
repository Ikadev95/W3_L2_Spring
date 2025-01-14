package com.epicode.gestione_viaggi.dipendente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DipendenteCreaRequest {
    @NotBlank(message = "l'username non può essere vuoto")
    private String username;
    @NotBlank(message = "il nome non può essere vuoto")
    private String nome;
    @NotBlank(message = "il cognome non può essere vuoto")
    private String cognome;
    @Email(message ="la mail deve essere valida")
    private String email;
}
