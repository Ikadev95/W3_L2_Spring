package com.epicode.gestione_viaggi.dipendente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepo extends JpaRepository<Dipendente, Long> {
    boolean existsByEmail(String email);
    boolean existsByNome(String username);
}
