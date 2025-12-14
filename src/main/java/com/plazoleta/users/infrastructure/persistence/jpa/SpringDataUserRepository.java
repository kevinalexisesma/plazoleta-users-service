package com.plazoleta.users.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByCorreoIgnoreCase(String correo);

    boolean existsByDocumentoIdentidad(String documentoIdentidad);

    Optional<UserEntity> findByCorreoIgnoreCase(String correo);
}
