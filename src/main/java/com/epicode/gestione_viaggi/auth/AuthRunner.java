package com.epicode.gestione_viaggi.auth;

import com.epicode.gestione_viaggi.auth.requests_and_responses.RegisterRequest;
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
        RegisterRequest request = new RegisterRequest();
        request.setCognome("Admin");
        request.setNome("Admin");
        request.setUsername("admin");
        request.setPassword("adminpwd");
        request.setEmail("admin@example.com");
        if (adminUser.isEmpty()) {
            appUserService.registerUser(
                    Set.of(Role.ROLE_ADMIN),
                    request

            );
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("dipendente");
        RegisterRequest requestUser = new RegisterRequest();
        requestUser.setCognome("Dipendente");
        requestUser.setNome("Dipendente");
        requestUser.setUsername("dipendente");
        requestUser.setPassword("dipendentepwd");
        requestUser.setEmail("dipendente@example.com");
        if (normalUser.isEmpty()) {
            appUserService.registerUser(
                    Set.of(Role.ROLE_DIPENDENTE),
                    requestUser

            );
        }
    }
}
