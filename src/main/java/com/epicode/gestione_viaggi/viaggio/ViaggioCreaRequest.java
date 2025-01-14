package com.epicode.gestione_viaggi.viaggio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioCreaRequest {
    @NotBlank
    private String destinazione;
    @NotNull
    private LocalDate data;
    @NotNull
    private StatoViaggio statoViaggio;
}
