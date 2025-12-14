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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.infrastructure.persistence.entity.RoleEntity;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataRoleRepository;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataUserRepository;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeUseCaseTest {

    @Mock
    private SpringDataUserRepository userRepository;
    @Mock
    private SpringDataRoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateEmployeeUseCase useCase;

    @Test
    void shouldCreateEmployeeSuccessfully() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(
                "Juan", "Pérez", "123456789", "+573001112233", LocalDate.of(1990, 1, 1), "empleado@plazoleta.com",
                "empleado123");

        RoleEntity employeeRole = new RoleEntity();
        employeeRole.setId(2);
        employeeRole.setName("EMPLOYEE");

        when(roleRepository.findByName("EMPLOYEE")).thenReturn(Optional.of(employeeRole));
        when(passwordEncoder.encode("empleado123")).thenReturn("bcryptHash");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        UserEntity result = useCase.handle(request);

        assertEquals("Juan", result.getNombre());
        assertEquals("EMPLOYEE", result.getRol().getName());
        assertEquals("bcryptHash", result.getClaveHash());
    }
}
