package com.plazoleta.users.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateEmployeeRequest(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @Pattern(regexp = "^\\d+$") String documentoIdentidad,
        @Pattern(regexp = "^\\+?\\d{1,13}$") String celular,
        @NotNull @Past LocalDate fechaNacimiento,
        @Email @NotBlank String correo,
        @NotBlank @Size(min = 8, max = 100) String clave) {
}
