package springboot.application.security.usecase;

import java.time.Duration;
import java.time.Instant;

import springboot.application.security.command.LoginCommand;
import springboot.application.security.dto.AuthTokensResponse;
import springboot.domain.security.exception.InvalidCredentialsException;
import springboot.domain.security.model.AuthUser;
import springboot.domain.security.model.RefreshToken;
import springboot.domain.security.port.AuthUserRepository;
import springboot.domain.security.port.PasswordService;
import springboot.domain.security.port.RefreshTokenRepository;
import springboot.domain.security.port.TokenService;

public class LoginUseCase {
    private final AuthUserRepository users;
    private final PasswordService passwords;
    private final TokenService tokens;
    private final RefreshTokenRepository refreshTokens;
    private final Duration accessTokenLifetime;
    private final Duration refreshTokenLifetime;

    public LoginUseCase(AuthUserRepository users, PasswordService passwords, TokenService tokens,
            RefreshTokenRepository refreshTokens, Duration accessTokenLifetime,
            Duration refreshTokenLifetime) {
        this.users = users;
        this.passwords = passwords;
        this.tokens = tokens;
        this.refreshTokens = refreshTokens;
        this.accessTokenLifetime = accessTokenLifetime;
        this.refreshTokenLifetime = refreshTokenLifetime;
    }

    public AuthTokensResponse execute(LoginCommand command) {
        AuthUser user = users.findByEmail(command.email())
                .orElseThrow(InvalidCredentialsException::new);
        if (!user.isActive() || !passwords.matches(command.password(), user.passwordHash())) {
            throw new InvalidCredentialsException();
        }
        String accessToken = tokens.generateAccessToken(user.id(), user.email(), user.roles());
        String refreshTokenValue = tokens.generateRefreshToken();
        RefreshToken refreshToken = RefreshToken.create(user.id(), refreshTokenValue,
                Instant.now().plus(refreshTokenLifetime));
        refreshTokens.save(refreshToken);
        return AuthTokensResponse.bearer(accessToken, refreshTokenValue,
                accessTokenLifetime.toSeconds());
    }
}
