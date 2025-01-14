package com.epicode.gestione_viaggi.auth;

import com.epicode.gestione_viaggi.auth.requests_and_responses.AuthResponse;
import com.epicode.gestione_viaggi.auth.requests_and_responses.LoginRequest;
import com.epicode.gestione_viaggi.auth.requests_and_responses.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        appUserService.registerUser(
                Set.of(Role.ROLE_DIPENDENTE),
                registerRequest
        );
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = appUserService.authenticateUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
