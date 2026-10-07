package springboot.domain.security.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import springboot.domain.security.exception.SecurityDomainException;

public final class AuthUser {
    private final UUID id;
    private final String email;
    private String passwordHash;
    private final Set<AuthRole> roles;
    private UserStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    private AuthUser(UUID id, String email, String passwordHash, Set<AuthRole> roles,
            UserStatus status, Instant createdAt, Instant updatedAt) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.email = normalizeEmail(email);
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new SecurityDomainException("La contraseña es obligatoria");
        }
        this.passwordHash = passwordHash;
        this.roles = new HashSet<>(roles == null ? Set.of() : roles);
        this.status = Objects.requireNonNull(status, "El estado es obligatorio");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt es obligatorio");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt es obligatorio");
    }

    public static AuthUser register(String email, String passwordHash) {
        Instant now = Instant.now();
        return new AuthUser(UUID.randomUUID(), email, passwordHash, Set.of(),
                UserStatus.ACTIVE, now, now);
    }

    public static AuthUser reconstruct(UUID id, String email, String passwordHash,
            Set<AuthRole> roles, UserStatus status, Instant createdAt, Instant updatedAt) {
        return new AuthUser(id, email, passwordHash, roles, status, createdAt, updatedAt);
    }

    public void assignRole(AuthRole role) {
        roles.add(Objects.requireNonNull(role, "El rol es obligatorio"));
        updatedAt = Instant.now();
    }

    public void changePassword(String newPasswordHash) {
        if (newPasswordHash == null || newPasswordHash.isBlank()) {
            throw new SecurityDomainException("La nueva contraseña es obligatoria");
        }
        passwordHash = newPasswordHash;
        updatedAt = Instant.now();
    }

    public boolean isActive() { return status == UserStatus.ACTIVE; }
    public UUID id() { return id; }
    public String email() { return email; }
    public String passwordHash() { return passwordHash; }
    public Set<AuthRole> roles() { return Set.copyOf(roles); }
    public UserStatus status() { return status; }
    public Instant createdAt() { return createdAt; }
    public Instant updatedAt() { return updatedAt; }

    private static String normalizeEmail(String value) {
        if (value == null || !value.trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new SecurityDomainException("Email inválido");
        }
        return value.trim().toLowerCase();
    }
}
