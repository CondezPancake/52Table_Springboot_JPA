package springboot.application.security.usecase;

import java.time.Duration;

import springboot.application.security.dto.AuthTokensResponse;
import springboot.domain.security.exception.InvalidCredentialsException;
import springboot.domain.security.model.AuthUser;
import springboot.domain.security.model.RefreshToken;
import springboot.domain.security.port.AuthUserRepository;
import springboot.domain.security.port.RefreshTokenRepository;
import springboot.domain.security.port.TokenService;

public class RefreshAccessTokenUseCase {
    private final RefreshTokenRepository refreshTokens;
    private final AuthUserRepository users;
    private final TokenService tokens;
    private final Duration accessTokenLifetime;

    public RefreshAccessTokenUseCase(RefreshTokenRepository refreshTokens,
            AuthUserRepository users, TokenService tokens, Duration accessTokenLifetime) {
        this.refreshTokens = refreshTokens;
        this.users = users;
        this.tokens = tokens;
        this.accessTokenLifetime = accessTokenLifetime;
    }

    public AuthTokensResponse execute(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokens.findByToken(refreshTokenValue)
                .orElseThrow(() -> new InvalidCredentialsException("Refresh token inválido"));
        if (!refreshToken.isValid()) {
            throw new InvalidCredentialsException("Refresh token expirado o revocado");
        }
        AuthUser user = users.findById(refreshToken.userId())
                .orElseThrow(InvalidCredentialsException::new);
        if (!user.isActive()) {
            throw new InvalidCredentialsException("Usuario bloqueado o inactivo");
        }
        String accessToken = tokens.generateAccessToken(user.id(), user.email(), user.roles());
        return AuthTokensResponse.bearer(accessToken, refreshTokenValue,
                accessTokenLifetime.toSeconds());
    }
}
