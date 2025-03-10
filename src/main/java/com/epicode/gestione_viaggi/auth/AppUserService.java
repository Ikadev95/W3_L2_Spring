package com.epicode.gestione_viaggi.auth;

import com.epicode.gestione_viaggi.auth.requests_and_responses.RegisterRequest;
import com.epicode.gestione_viaggi.dipendente.Dipendente;
import com.epicode.gestione_viaggi.dipendente.DipendenteRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private DipendenteRepo dipendenteRepo;

    public AppUser registerUser(Set<Role> roles, RegisterRequest registerRequest) {
        if (appUserRepository.existsByUsername(registerRequest.getUsername())) {
            throw new EntityExistsException("Username già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(registerRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setRoles(roles);


        Dipendente dipendente = new Dipendente();
        dipendente.setNome(registerRequest.getNome());
        dipendente.setCognome(registerRequest.getCognome());
        dipendente.setEmail(registerRequest.getEmail());

        appUser.setDipendente(dipendente);

        // Associa il Dipendente all'AppUser
        dipendente.setAppUser(appUser);

        // Salva prima l'utente
        appUser = appUserRepository.save(appUser);

        // Salva anche il Dipendente
        dipendenteRepo.save(dipendente);

        return appUser;
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public String authenticateUser(String email, String password)  {


           AppUser u =  appUserRepository.findUserByEmail(email)
          .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con email: " + email));

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(u.getUsername(), password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }


    public AppUser loadUserByUsername(String username)  {
        AppUser appUser = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));


        return appUser;
    }
}
