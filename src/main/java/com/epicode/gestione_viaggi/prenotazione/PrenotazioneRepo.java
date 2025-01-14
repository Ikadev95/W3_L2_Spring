package com.epicode.gestione_viaggi.prenotazione;

import com.epicode.gestione_viaggi.dipendente.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione,Long> {
    @Query("SELECT COUNT(p) " +
            "FROM Prenotazione p " +
            "JOIN p.viaggio v " +
            "WHERE p.dipendente = :dipendente AND v.data = :data")
    long countPrenotazioniByDipendenteAndData(@Param("dipendente") Dipendente dipendente,
                                              @Param("data") LocalDate data);

    // Trova prenotazioni per un utente tramite il dipendente
    @Query("SELECT p FROM Prenotazione p WHERE p.dipendente.appUser.username = :username")
    List<Prenotazione> findByUtente(@Param("username") String username);
}
