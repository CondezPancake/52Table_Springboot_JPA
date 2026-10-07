package springboot.domain.security.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class RefreshToken {
    private final UUID id;
    private final UUID userId;
    private final String token;
    private final Instant expiresAt;
    private boolean revoked;
    private final Instant createdAt;

    private RefreshToken(UUID id, UUID userId, String token, Instant expiresAt,
            boolean revoked, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.token = Objects.requireNonNull(token);
        this.expiresAt = Objects.requireNonNull(expiresAt);
        this.revoked = revoked;
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static RefreshToken create(UUID userId, String token, Instant expiresAt) {
        return new RefreshToken(UUID.randomUUID(), userId, token, expiresAt, false, Instant.now());
    }

    public static RefreshToken reconstruct(UUID id, UUID userId, String token,
            Instant expiresAt, boolean revoked, Instant createdAt) {
        return new RefreshToken(id, userId, token, expiresAt, revoked, createdAt);
    }

    public boolean isValid() { return !revoked && Instant.now().isBefore(expiresAt); }
    public void revoke() { revoked = true; }
    public UUID id() { return id; }
    public UUID userId() { return userId; }
    public String token() { return token; }
    public Instant expiresAt() { return expiresAt; }
    public boolean revoked() { return revoked; }
    public Instant createdAt() { return createdAt; }
}
