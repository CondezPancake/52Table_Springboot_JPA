package springboot.domain.security.port;

import java.util.Optional;

import springboot.domain.security.model.RefreshToken;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
}
