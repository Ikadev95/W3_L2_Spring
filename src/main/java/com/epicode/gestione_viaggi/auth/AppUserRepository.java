package com.epicode.gestione_viaggi.auth;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
    @Query("SELECT u FROM AppUser u JOIN u.dipendente d WHERE d.email = :email")
    Optional<AppUser> findUserByEmail(@Param("email") String email);
}
