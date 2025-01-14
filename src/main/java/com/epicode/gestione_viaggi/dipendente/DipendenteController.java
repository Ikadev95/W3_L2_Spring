package com.epicode.gestione_viaggi.dipendente;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dipendenti")
@PreAuthorize("isAuthenticated()")
public class DipendenteController {
    final private DipendenteService dipendenteService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Dipendente>> getDipendenti(){
        List<Dipendente> dipendenti = dipendenteService.findAllDipendenti();
        return ResponseEntity.ok(dipendenti);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable Long id){
        Dipendente dipendente = dipendenteService.findDipendenteById(id);
        return ResponseEntity.ok(dipendente);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(@RequestBody DipendenteCreaRequest request){
        return new ResponseEntity<>(dipendenteService.createDipendente(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('DIPENDENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> modifyDipendente(@PathVariable Long id, @RequestBody DipendenteCreaRequest d){
        return  ResponseEntity.ok(dipendenteService.modifyDipendente(id, d));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDipendente(@PathVariable Long id){
        dipendenteService.deleteDipendente(id);
        return new ResponseEntity<>("dipendente eliminato", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('DIPENDENTE')")
    @PostMapping(path="/foto/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Map> uploadFoto(@RequestPart("file") MultipartFile file, @PathVariable Long id) {
        String folder = "test";

        Map result = dipendenteService.uploadeFoto(file, folder,id);

        return ResponseEntity.ok(result);
    }
}
