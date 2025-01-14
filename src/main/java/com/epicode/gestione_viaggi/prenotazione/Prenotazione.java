package com.epicode.gestione_viaggi.prenotazione;

import com.epicode.gestione_viaggi.dipendente.Dipendente;
import com.epicode.gestione_viaggi.viaggio.Viaggio;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    private LocalDate dataPrenotazione = LocalDate.now();
    private String note;
}