package com.epicode.gestione_viaggi.prenotazione;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PreAuthorize("hasRole('DIPENDENTE')")
    @PostMapping
    public ResponseEntity<Prenotazione> savePrenotazione(PrenotazioneCreaRequest p){
        return new ResponseEntity<>(prenotazioneService.savePrenotazione(p), HttpStatus.CREATED);
    }

    @GetMapping("/recupero_dipendente")
    public List<Prenotazione> getPrenotazioniConRecuperoDipendente(@AuthenticationPrincipal User utente) {

        List<Prenotazione> prenotazioni = prenotazioneService.findByUtente(utente.getUsername());

        System.out.println(utente);
        return prenotazioni;
    }
}
