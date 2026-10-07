package springboot.domain.security.port;

import java.util.Optional;
import java.util.UUID;

import springboot.domain.security.model.AuthUser;

public interface AuthUserRepository {
    AuthUser save(AuthUser user);
    Optional<AuthUser> findById(UUID id);
    Optional<AuthUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
