package springboot.application.security.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import springboot.domain.security.exception.InvalidCredentialsException;
import springboot.domain.security.model.RefreshToken;
import springboot.domain.security.port.RefreshTokenRepository;

class LogoutUseCaseTest {
    @Test
    void revokesOnlyTheAuthenticatedUsersRefreshToken() {
        UUID ownerId = UUID.randomUUID();
        InMemoryRefreshTokens repository = new InMemoryRefreshTokens(
                RefreshToken.create(ownerId, "refresh-token", Instant.now().plusSeconds(60)));
        LogoutUseCase useCase = new LogoutUseCase(repository);

        useCase.execute(ownerId, "refresh-token");

        assertTrue(repository.token.revoked());
    }

    @Test
    void rejectsARefreshTokenOwnedByAnotherUser() {
        InMemoryRefreshTokens repository = new InMemoryRefreshTokens(
                RefreshToken.create(UUID.randomUUID(), "refresh-token", Instant.now().plusSeconds(60)));
        LogoutUseCase useCase = new LogoutUseCase(repository);

        assertThrows(InvalidCredentialsException.class,
                () -> useCase.execute(UUID.randomUUID(), "refresh-token"));
    }

    private static final class InMemoryRefreshTokens implements RefreshTokenRepository {
        private RefreshToken token;

        private InMemoryRefreshTokens(RefreshToken token) {
            this.token = token;
        }

        @Override
        public RefreshToken save(RefreshToken token) {
            this.token = token;
            return token;
        }

        @Override
        public Optional<RefreshToken> findByToken(String value) {
            return token.token().equals(value) ? Optional.of(token) : Optional.empty();
        }
    }
}
