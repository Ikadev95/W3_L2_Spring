package com.epicode.gestione_viaggi.viaggio;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/viaggi")
@PreAuthorize("isAuthenticated()")
public class ViaggioController {

    final private ViaggioService viaggioService;

    @GetMapping
    public ResponseEntity<List<Viaggio>> getAllViaggi(){
        return ResponseEntity.ok(viaggioService.getAllViaggi());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> geViaggioById(@PathVariable Long id){
        return ResponseEntity.ok(viaggioService.getViaggioById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody ViaggioCreaRequest v){
        return new ResponseEntity<>(viaggioService.createViaggio(v), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> modifyViaggio(@PathVariable Long id, @RequestBody ViaggioCreaRequest v){
        return ResponseEntity.ok(viaggioService.modifyViaggio(id,v));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViaggio(@PathVariable Long id){
        viaggioService.deleteViaggio(id);
        return new ResponseEntity<>("viaggio eliminato", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchStato(@PathVariable Long id, StatoViaggio stato ){
        Viaggio v = viaggioService.patchStato(id,stato);
        return ResponseEntity.ok(v);
    }

}
