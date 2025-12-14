package com.plazoleta.users.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.users.infrastructure.persistence.entity.RoleEntity;

public interface SpringDataRoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
