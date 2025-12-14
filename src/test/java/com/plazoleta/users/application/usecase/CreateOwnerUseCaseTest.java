package com.plazoleta.users.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.dto.UserResponse;
import com.plazoleta.users.domain.service.UserDomainService;
import com.plazoleta.users.infrastructure.persistence.entity.RoleEntity;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataRoleRepository;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataUserRepository;

@ExtendWith(MockitoExtension.class)
class CreateOwnerUseCaseTest {
    @Mock
    SpringDataUserRepository userRepo;
    @Mock
    SpringDataRoleRepository roleRepo;
    @Mock
    PasswordEncoder encoder;
    @InjectMocks
    CreateOwnerUseCase useCase;
    @Spy
    UserDomainService domainService = new UserDomainService();

    @Test
    void creaPropietarioMayorDeEdad_ok() {
        when(roleRepo.findByName("OWNER")).thenReturn(Optional.of(new RoleEntity() {
            {
                setId(2);
                setName("OWNER");
            }
        }));
        when(userRepo.existsByCorreoIgnoreCase(any())).thenReturn(false);
        when(userRepo.existsByDocumentoIdentidad(any())).thenReturn(false);
        when(encoder.encode(any())).thenReturn("$bcrypt$hash");
        when(userRepo.save(any())).thenAnswer(inv -> {
            UserEntity u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });

        CreateOwnerRequest req = new CreateOwnerRequest("Kevin", "López", "12345", "+573001234567",
                LocalDate.now().minusYears(20), "kevin@example.com", "supersecret");

        UserResponse resp = useCase.handle(req);
        assertEquals("OWNER", resp.rol());
        assertEquals(1L, resp.id());
    }
}
