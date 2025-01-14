package com.epicode.gestione_viaggi.viaggio;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class ViaggioService {
    final private ViaggioRepo viaggioRepo;

    //restituisco tutti i viaggi
    public List<Viaggio> getAllViaggi(){
        return viaggioRepo.findAll();
    }

    //restituisco un viaggio cercandolo per id
    public Viaggio getViaggioById(Long id){
        if(!viaggioRepo.existsById(id)){
            throw new EntityNotFoundException("il viaggio non è stato trovato");
        }
        return viaggioRepo.findById(id).get();
    }

    //modifico un viaggio
    public Viaggio modifyViaggio(Long id, @Valid ViaggioCreaRequest v){
        Viaggio viaggio = getViaggioById(id);
        BeanUtils.copyProperties(v,viaggio);
        return viaggioRepo.save(viaggio);
    }

    //creo un viaggio
    public Viaggio createViaggio(@Valid ViaggioCreaRequest v){
        Viaggio viaggio = new Viaggio();
        if(viaggioRepo.existsByDestinazioneAndData(v.getDestinazione(), v.getData())){
            throw new EntityExistsException("Il viaggio è già esistente");
        }

        BeanUtils.copyProperties(v,viaggio);
        return viaggioRepo.save(viaggio);
    }

    //elimino un viaggio
    public Boolean deleteViaggio (Long id){
        if(!viaggioRepo.existsById(id)){
            throw new EntityNotFoundException("il viaggio da eliminare non è stato trovato");
        }
        viaggioRepo.delete(getViaggioById(id));
        return true;
    }

    //modifico lo stato del viaggio
    public Viaggio patchStato(Long id, StatoViaggio statoViaggio){
        Viaggio v = getViaggioById(id);
        v.setStatoViaggio(statoViaggio);
        return viaggioRepo.save(v);
    }
}
