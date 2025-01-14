package com.epicode.gestione_viaggi.viaggio;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ViaggioRepo extends JpaRepository<Viaggio,Long> {
    Boolean existsByDestinazione(String d);
    Boolean existsByData(LocalDate data);
    boolean existsByDestinazioneAndData(String destinazione, LocalDate data);
}
