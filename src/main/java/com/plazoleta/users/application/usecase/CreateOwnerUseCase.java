package com.plazoleta.users.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.dto.UserResponse;
import com.plazoleta.users.domain.service.UserDomainService;
import com.plazoleta.users.infrastructure.persistence.entity.RoleEntity;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataRoleRepository;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateOwnerUseCase {
    private final SpringDataUserRepository userRepo;
    private final SpringDataRoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final UserDomainService domainService;

    @Transactional
    public UserResponse handle(CreateOwnerRequest req) {
        if (!domainService.esMayorDeEdad(req.fechaNacimiento())) {
            throw new IllegalArgumentException("El usuario debe ser mayor de edad");
        }
        RoleEntity ownerRole = roleRepo.findByName("OWNER")
                .orElseThrow(() -> new IllegalStateException("Rol OWNER no configurado"));

        if (userRepo.existsByCorreoIgnoreCase(req.correo())) {
            throw new IllegalArgumentException("Correo ya registrado");
        }
        if (userRepo.existsByDocumentoIdentidad(req.documentoIdentidad())) {
            throw new IllegalArgumentException("Documento ya registrado");
        }

        UserEntity user = new UserEntity();
        user.setNombre(req.nombre());
        user.setApellido(req.apellido());
        user.setDocumentoIdentidad(req.documentoIdentidad());
        user.setCelular(req.celular());
        user.setFechaNacimiento(req.fechaNacimiento());
        user.setCorreo(req.correo().toLowerCase());
        user.setClaveHash(encoder.encode(req.clave()));
        user.setRol(ownerRole);

        UserEntity saved = userRepo.save(user);
        return new UserResponse(
                saved.getId(), saved.getNombre(), saved.getApellido(),
                saved.getDocumentoIdentidad(), saved.getCelular(),
                saved.getFechaNacimiento(), saved.getCorreo(), saved.getRol().getName());
    }
}
