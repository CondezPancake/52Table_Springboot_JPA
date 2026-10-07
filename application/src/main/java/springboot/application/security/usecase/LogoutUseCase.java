package springboot.application.security.usecase;

import java.util.UUID;

import springboot.domain.security.exception.InvalidCredentialsException;
import springboot.domain.security.model.RefreshToken;
import springboot.domain.security.port.RefreshTokenRepository;

public class LogoutUseCase {
    private final RefreshTokenRepository refreshTokens;

    public LogoutUseCase(RefreshTokenRepository refreshTokens) {
        this.refreshTokens = refreshTokens;
    }

    public void execute(UUID authenticatedUserId, String refreshTokenValue) {
        RefreshToken token = refreshTokens.findByToken(refreshTokenValue)
                .orElseThrow(() -> new InvalidCredentialsException("Refresh token inválido"));
        if (!token.userId().equals(authenticatedUserId)) {
            throw new InvalidCredentialsException("El refresh token no pertenece al usuario autenticado");
        }
        token.revoke();
        refreshTokens.save(token);
    }
}
