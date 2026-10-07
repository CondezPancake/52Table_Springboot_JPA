package springboot.domain.security.model;

import java.util.Objects;
import java.util.UUID;

import springboot.domain.security.exception.SecurityDomainException;

public final class AuthRole {
    private final UUID id;
    private final String name;
    private final String authority;

    private AuthRole(UUID id, String name, String authority) {
        this.id = Objects.requireNonNull(id, "El id del rol es obligatorio");
        if (name == null || name.isBlank() || authority == null || authority.isBlank()) {
            throw new SecurityDomainException("El nombre y authority del rol son obligatorios");
        }
        this.name = name.trim().toUpperCase();
        this.authority = authority.trim().toUpperCase();
    }

    public static AuthRole reconstruct(UUID id, String name, String authority) {
        return new AuthRole(id, name, authority);
    }

    public UUID id() { return id; }
    public String name() { return name; }
    public String authority() { return authority; }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof AuthRole role && id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
