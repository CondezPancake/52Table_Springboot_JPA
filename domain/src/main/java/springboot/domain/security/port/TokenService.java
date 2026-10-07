package springboot.domain.security.port;

import java.util.Set;
import java.util.UUID;

import springboot.domain.security.model.AuthRole;

public interface TokenService {
    String generateAccessToken(UUID userId, String email, Set<AuthRole> roles);
    String generateRefreshToken();
    UUID validateAndGetUserId(String token);
    Set<String> extractAuthorities(String token);
}
