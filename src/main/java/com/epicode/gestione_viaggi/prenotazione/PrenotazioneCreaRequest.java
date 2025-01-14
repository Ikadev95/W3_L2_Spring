package com.epicode.gestione_viaggi.prenotazione;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneCreaRequest {
    @NotNull
    private Long idDipendente;
    @NotNull
    private Long idViaggio;
    private String note;
}
