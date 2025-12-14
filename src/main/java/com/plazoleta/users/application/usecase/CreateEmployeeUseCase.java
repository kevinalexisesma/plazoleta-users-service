package com.plazoleta.users.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.infrastructure.persistence.entity.RoleEntity;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataRoleRepository;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCase {

    private final SpringDataUserRepository userRepository;
    private final SpringDataRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity handle(CreateEmployeeRequest request) {
        RoleEntity employeeRole = roleRepository.findByName("EMPLOYEE")
                .orElseThrow(() -> new RuntimeException("Rol EMPLOYEE no existe"));

        UserEntity user = new UserEntity();
        user.setNombre(request.nombre());
        user.setApellido(request.apellido());
        user.setDocumentoIdentidad(request.documentoIdentidad());
        user.setCelular(request.celular());
        user.setCorreo(request.correo());
        user.setFechaNacimiento(request.fechaNacimiento());
        user.setClaveHash(passwordEncoder.encode(request.clave()));
        user.setRol(employeeRole);
        user.setEstado("ACTIVO");

        return userRepository.save(user);
    }
}
