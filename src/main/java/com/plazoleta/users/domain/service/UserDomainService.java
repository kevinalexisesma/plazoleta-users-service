package com.plazoleta.users.domain.service;

import java.time.LocalDate;
import java.time.Period;

public class UserDomainService {
    public boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
