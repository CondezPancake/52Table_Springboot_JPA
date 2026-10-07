package springboot.domain.security.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class AuthUserTest {
    @Test
    void registersAnActiveUserAndNormalizesTheEmail() {
        AuthUser user = AuthUser.register("  User@Example.com ", "bcrypt-hash");
        AuthRole role = AuthRole.reconstruct(UUID.randomUUID(), "user", "role_user");

        user.assignRole(role);

        assertEquals("user@example.com", user.email());
        assertTrue(user.isActive());
        assertEquals("ROLE_USER", user.roles().iterator().next().authority());
    }
}
