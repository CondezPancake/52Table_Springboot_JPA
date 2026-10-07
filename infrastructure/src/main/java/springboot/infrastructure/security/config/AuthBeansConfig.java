package springboot.infrastructure.security.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springboot.application.security.usecase.ChangePasswordUseCase;
import springboot.application.security.usecase.AssignRoleUseCase;
import springboot.application.security.usecase.GetCurrentUserUseCase;
import springboot.application.security.usecase.LoginUseCase;
import springboot.application.security.usecase.LogoutUseCase;
import springboot.application.security.usecase.RefreshAccessTokenUseCase;
import springboot.application.security.usecase.RegisterUserUseCase;
import springboot.domain.security.port.AuthRoleRepository;
import springboot.domain.security.port.AuthUserRepository;
import springboot.domain.security.port.PasswordService;
import springboot.domain.security.port.RefreshTokenRepository;
import springboot.domain.security.port.TokenService;
import springboot.infrastructure.security.persistence.adapter.AuthRoleRepositoryAdapter;
import springboot.infrastructure.security.persistence.adapter.AuthUserRepositoryAdapter;
import springboot.infrastructure.security.persistence.adapter.RefreshTokenRepositoryAdapter;
import springboot.infrastructure.security.persistence.repository.AuthRoleJpaRepository;
import springboot.infrastructure.security.persistence.repository.AuthUserJpaRepository;
import springboot.infrastructure.security.persistence.repository.RefreshTokenJpaRepository;

@Configuration
public class AuthBeansConfig {
    @Bean
    AuthRoleRepository authRoleRepository(AuthRoleJpaRepository repository) {
        return new AuthRoleRepositoryAdapter(repository);
    }

    @Bean
    AuthUserRepository authUserRepository(AuthUserJpaRepository users, AuthRoleJpaRepository roles) {
        return new AuthUserRepositoryAdapter(users, roles);
    }

    @Bean
    RefreshTokenRepository refreshTokenRepository(RefreshTokenJpaRepository repository) {
        return new RefreshTokenRepositoryAdapter(repository);
    }

    @Bean
    RegisterUserUseCase registerUserUseCase(AuthUserRepository users, AuthRoleRepository roles,
            PasswordService passwords) {
        return new RegisterUserUseCase(users, roles, passwords);
    }

    @Bean
    LoginUseCase loginUseCase(AuthUserRepository users, PasswordService passwords,
            TokenService tokens, RefreshTokenRepository refreshTokens,
            @Value("${jwt.access-token-expiration}") long accessMillis,
            @Value("${jwt.refresh-token-expiration}") long refreshMillis) {
        return new LoginUseCase(users, passwords, tokens, refreshTokens,
                Duration.ofMillis(accessMillis), Duration.ofMillis(refreshMillis));
    }

    @Bean
    RefreshAccessTokenUseCase refreshAccessTokenUseCase(RefreshTokenRepository refreshTokens,
            AuthUserRepository users, TokenService tokens,
            @Value("${jwt.access-token-expiration}") long accessMillis) {
        return new RefreshAccessTokenUseCase(refreshTokens, users, tokens, Duration.ofMillis(accessMillis));
    }

    @Bean
    LogoutUseCase logoutUseCase(RefreshTokenRepository refreshTokens) {
        return new LogoutUseCase(refreshTokens);
    }

    @Bean
    GetCurrentUserUseCase getCurrentUserUseCase(AuthUserRepository users) {
        return new GetCurrentUserUseCase(users);
    }

    @Bean
    ChangePasswordUseCase changePasswordUseCase(AuthUserRepository users, PasswordService passwords) {
        return new ChangePasswordUseCase(users, passwords);
    }

    @Bean
    AssignRoleUseCase assignRoleUseCase(AuthUserRepository users, AuthRoleRepository roles) {
        return new AssignRoleUseCase(users, roles);
    }
}
