package com.epicode.gestione_viaggi.viaggio;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String destinazione;
    private LocalDate data;
    private StatoViaggio statoViaggio;
}