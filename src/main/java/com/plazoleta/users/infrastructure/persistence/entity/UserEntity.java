package com.plazoleta.users.infrastructure.persistence.entity;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellido;
    @Column(nullable = false, unique = true, length = 30)
    private String documentoIdentidad;
    @Column(nullable = false, length = 13)
    private String celular;
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    @Column(nullable = false, unique = true, length = 150)
    private String correo;
    @Column(nullable = false, length = 120)
    private String claveHash;
    @ManyToOne(optional = false)
    @JoinColumn(name = "rol_id")
    private RoleEntity rol;
    @Column(nullable = false, length = 20)
    private String estado = "ACTIVO";
    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}
