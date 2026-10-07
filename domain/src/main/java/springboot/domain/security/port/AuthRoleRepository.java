package springboot.domain.security.port;

import java.util.Optional;

import springboot.domain.security.model.AuthRole;

public interface AuthRoleRepository {
    Optional<AuthRole> findByAuthority(String authority);
}
