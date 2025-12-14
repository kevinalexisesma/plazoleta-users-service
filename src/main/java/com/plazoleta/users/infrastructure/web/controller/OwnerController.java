package com.plazoleta.users.infrastructure.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.dto.UserResponse;
import com.plazoleta.users.application.usecase.CreateOwnerUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/owners")
@RequiredArgsConstructor
@Tag(name = "Owners", description = "Gestión de propietarios")
public class OwnerController {
    private final CreateOwnerUseCase createOwnerUseCase;

    @PostMapping
    @Operation(summary = "Crear propietario", description = "Crea una cuenta de propietario")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateOwnerRequest req) {
        UserResponse resp = createOwnerUseCase.handle(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
