package com.epicode.gestione_viaggi.auth;

import com.epicode.gestione_viaggi.dipendente.Dipendente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

// user bean
@Entity
@Table(name = "users")
@Data
public class  AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Dipendente dipendente;

}
