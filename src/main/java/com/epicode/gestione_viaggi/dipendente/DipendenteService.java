package com.epicode.gestione_viaggi.dipendente;

import com.cloudinary.Cloudinary;
import com.epicode.gestione_viaggi.exceptions.UploadException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class DipendenteService {
    @Autowired
    private DipendenteRepo dipendenteRepo;
    @Autowired
    private Cloudinary cloudinary;


    //restituisco tutti i dipendenti
    public List<Dipendente> findAllDipendenti(){
        return dipendenteRepo.findAll();
    }

    //restituisco un dipendente cercandolo per id
    public Dipendente findDipendenteById(Long id){
        if(!dipendenteRepo.existsById(id)){
            throw new EntityNotFoundException("il dipendente non è stato trovato");
        }
        return dipendenteRepo.findById(id).get();
    }

    //inserisco un nuovo dipendente
    public Dipendente createDipendente(@Valid DipendenteCreaRequest request){
        Dipendente dipendente = new Dipendente();
        if(dipendenteRepo.existsByEmail(request.getEmail()) || dipendenteRepo.existsByNome(request.getUsername())){
            throw new EntityExistsException("il dipendente con questa mail esiste già");
        }
        BeanUtils.copyProperties(request, dipendente);

        return dipendenteRepo.save(dipendente);
    }

    //modifico un dipendente
    public Dipendente modifyDipendente(Long id, @Valid DipendenteCreaRequest d){
        Dipendente dip = findDipendenteById(id);
        BeanUtils.copyProperties(d,dip);
        return dipendenteRepo.save(dip);
    }

    //elimino un dipendente
    public Boolean deleteDipendente(Long id){
        if(!dipendenteRepo.existsById(id)){
            throw new EntityNotFoundException("il dipendente da eliminare non è stato trovato");
        }
        dipendenteRepo.deleteById(id);
        return true;
    }

    public Map uploadeFoto(MultipartFile file, String folder, Long dipendenteId)  {

        try {
            Map result = cloudinary
                    .uploader()
                    .upload(file.getBytes(),
                            Cloudinary.asMap("folder", folder, "public_id", file.getOriginalFilename()));


            // Ottieni l'URL del file caricato
            String imageUrl = (String) result.get("secure_url");

            // Salva l'URL nel DB (esempio: associato a un dipendente)
            Dipendente dipendente = dipendenteRepo.findById(dipendenteId)
                    .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
            dipendente.setImageUrl(imageUrl);
            dipendenteRepo.save(dipendente);

            return result;
        } catch (IOException e) {
            throw new UploadException("Errore durante l'upload del file " + file.getOriginalFilename());
        }


    }
}
