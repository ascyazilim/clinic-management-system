package com.asc.clinicms.auth;

import com.asc.clinicms.auth.dto.*;
import com.asc.clinicms.security.JwtService;
import com.asc.clinicms.user.AppUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AppUserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        // role’u DB’den okuyalım (authority de olur ama DB net)
        var user = userRepo.findByUsername(req.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

        // expiresInSeconds: 60 dk varsayımı (app.jwt.access-token-exp-min ile uyumlu istersen burayı dinamik okuturuz)
        long expiresInSeconds = 60 * 60;

        return ResponseEntity.ok(new LoginResponse(
                token,
                "Bearer",
                expiresInSeconds,
                user.getRole().name(),
                user.getUsername()
        ));
    }
}
