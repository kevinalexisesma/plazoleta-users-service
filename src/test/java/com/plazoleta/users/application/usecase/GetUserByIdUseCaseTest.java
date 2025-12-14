package com.plazoleta.users.application.usecase;

import com.plazoleta.users.application.dto.UserBasicResponse;
import com.plazoleta.users.infrastructure.persistence.entity.RoleEntity;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserByIdUseCaseTest {

    @Mock
    SpringDataUserRepository userRepo;

    @InjectMocks
    GetUserByIdUseCase useCase;

    @Test
    void devuelveUsuarioPorId_ok() {
        RoleEntity role = new RoleEntity();
        role.setId(2);
        role.setName("OWNER");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setNombre("Kevin");
        user.setApellido("Lopez");
        user.setCorreo("kevin@example.com");
        user.setDocumentoIdentidad("123456");
        user.setCelular("+573001234567");
        user.setFechaNacimiento(LocalDate.of(2000, 1, 1));
        user.setClaveHash("$bcrypt$hash");
        user.setRol(role);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        UserBasicResponse resp = useCase.handle(1L);

        assertEquals(1L, resp.id());
        assertEquals("Kevin", resp.nombre());
        assertEquals("OWNER", resp.rol());
    }
}
