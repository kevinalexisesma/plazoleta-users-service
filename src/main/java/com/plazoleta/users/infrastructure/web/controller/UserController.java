package com.plazoleta.users.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.users.application.dto.UserBasicResponse;
import com.plazoleta.users.application.usecase.GetUserByIdUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Gestión de usuarios")
public class UserController {
    private final GetUserByIdUseCase getUserByIdUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve datos básicos y rol del usuario")
    public ResponseEntity<UserBasicResponse> getById(@PathVariable Long id) {
        UserBasicResponse resp = getUserByIdUseCase.handle(id);
        return ResponseEntity.ok(resp);
    }
}
