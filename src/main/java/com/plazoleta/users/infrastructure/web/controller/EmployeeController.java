package com.plazoleta.users.infrastructure.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.application.usecase.CreateEmployeeUseCase;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/owners/employees")
@RequiredArgsConstructor
@Tag(name = "Employees", description = "Gestión de empleados")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployeeUseCase;

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    @Operation(summary = "Crear empleado", description = "Crea una cuenta de empleado")
    public ResponseEntity<UserEntity> createEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        UserEntity employee = createEmployeeUseCase.handle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
}
