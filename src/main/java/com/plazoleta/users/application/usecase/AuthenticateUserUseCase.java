package com.plazoleta.users.application.usecase;

import com.plazoleta.users.application.dto.LoginRequest;
import com.plazoleta.users.application.dto.LoginResponse;
import com.plazoleta.users.infrastructure.persistence.entity.UserEntity;
import com.plazoleta.users.infrastructure.persistence.jpa.SpringDataUserRepository;
import com.plazoleta.users.infrastructure.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserUseCase {

    private final SpringDataUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponse handle(LoginRequest request) {
        UserEntity user = userRepository.findByCorreoIgnoreCase(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getClave(), user.getClaveHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtProvider.generateToken(
                user.getId(),
                user.getRol().getName(),
                user.getCorreo()
        );

        return new LoginResponse(token, user.getRol().getName());
    }
}
