package com.plazoleta.users.application.dto;

public record UserBasicResponse(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String rol) {
}
