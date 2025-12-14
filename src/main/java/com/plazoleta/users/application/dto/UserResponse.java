package com.plazoleta.users.application.dto;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String nombre,
        String apellido,
        String documentoIdentidad,
        String celular,
        LocalDate fechaNacimiento,
        String correo,
        String rol) {
}