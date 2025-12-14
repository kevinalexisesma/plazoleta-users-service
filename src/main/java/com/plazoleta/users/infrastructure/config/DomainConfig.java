package com.plazoleta.users.infrastructure.config;

import com.plazoleta.users.domain.service.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public UserDomainService userDomainService() {
        return new UserDomainService();
    }
}
