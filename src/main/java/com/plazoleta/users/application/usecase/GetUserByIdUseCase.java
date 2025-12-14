package com.plazoleta.users.application.usecase;

import org.springframework.stereotype.Service;

import com.plazoleta.users.application.dto.UserBasicResponse;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {
    private final SpringDataUserRepository userRepo;

    public UserBasicResponse handle(Long id) {
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return new UserBasicResponse(
                user.getId(),
                user.getNombre(),
                user.getApellido(),
                user.getCorreo(),
                user.getRol().getName());
    }
}
