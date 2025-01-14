package com.epicode.gestione_viaggi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            appUserService.registerUser(
                    "admin",
                    "adminpwd",
                    Set.of(Role.ROLE_ADMIN),
                    "Admin",
                    "Admin",
                    "admin@example.com"
            );
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("dipendente");
        if (normalUser.isEmpty()) {
            appUserService.registerUser(
                    "dipendente",
                    "dipendentepwd",
                    Set.of(Role.ROLE_DIPENDENTE),
                    "Giovanni",      // Nome dell'utente
                    "Rossi",         // Cognome dell'utente
                    "giovanni.rossi@example.com"
            );
        }
    }
}
