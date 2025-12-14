package com.plazoleta.users.infrastructure.web.controller;

import com.plazoleta.users.application.dto.LoginRequest;
import com.plazoleta.users.application.dto.LoginResponse;
import com.plazoleta.users.application.usecase.AuthenticateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticateUserUseCase.handle(request));
    }
}
