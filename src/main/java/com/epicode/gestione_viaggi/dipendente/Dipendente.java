package com.epicode.gestione_viaggi.dipendente;

import com.epicode.gestione_viaggi.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private String imageUrl;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AppUser appUser;
}