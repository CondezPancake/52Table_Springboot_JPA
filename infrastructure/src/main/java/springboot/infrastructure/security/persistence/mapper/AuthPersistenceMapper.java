package springboot.infrastructure.security.persistence.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import springboot.domain.security.model.AuthRole;
import springboot.domain.security.model.AuthUser;
import springboot.domain.security.model.RefreshToken;
import springboot.infrastructure.security.persistence.entity.AuthRoleJpaEntity;
import springboot.infrastructure.security.persistence.entity.AuthUserJpaEntity;
import springboot.infrastructure.security.persistence.entity.RefreshTokenJpaEntity;

public final class AuthPersistenceMapper {
    private AuthPersistenceMapper() { }

    public static AuthRole toDomain(AuthRoleJpaEntity entity) {
        return AuthRole.reconstruct(entity.getId(), entity.getName(), entity.getAuthority());
    }

    public static AuthUser toDomain(AuthUserJpaEntity entity) {
        Set<AuthRole> roles = entity.getRoles().stream()
                .map(AuthPersistenceMapper::toDomain)
                .collect(Collectors.toSet());
        return AuthUser.reconstruct(entity.getId(), entity.getEmail(), entity.getPasswordHash(),
                roles, entity.getStatus(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static AuthUserJpaEntity toJpa(AuthUser user, Set<AuthRoleJpaEntity> roles) {
        return new AuthUserJpaEntity(user.id(), user.email(), user.passwordHash(), user.status(),
                user.createdAt(), user.updatedAt(), roles);
    }

    public static RefreshToken toDomain(RefreshTokenJpaEntity entity) {
        return RefreshToken.reconstruct(entity.getId(), entity.getUserId(), entity.getToken(),
                entity.getExpiresAt(), entity.isRevoked(), entity.getCreatedAt());
    }

    public static RefreshTokenJpaEntity toJpa(RefreshToken token) {
        return new RefreshTokenJpaEntity(token.id(), token.userId(), token.token(),
                token.expiresAt(), token.revoked(), token.createdAt());
    }
}
