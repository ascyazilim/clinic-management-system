package com.asc.clinicms.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AppUserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        seed("admin", "Admin123!", Role.ADMIN);
        seed("doctor1", "Doctor123!", Role.DOCTOR);
        seed("sekreter1", "Sekreter123!", Role.SECRETARY);
    }

    private void seed(String username, String rawPassword, Role role) {
        if (repo.existsByUsername(username)) return;

        repo.save(AppUser.builder()
                .username(username)
                .passwordHash(encoder.encode(rawPassword))
                .role(role)
                .enabled(true)
                .build());
    }
}
