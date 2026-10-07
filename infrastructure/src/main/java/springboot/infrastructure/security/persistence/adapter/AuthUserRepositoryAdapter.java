package springboot.infrastructure.security.persistence.adapter;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import springboot.domain.security.model.AuthUser;
import springboot.domain.security.port.AuthUserRepository;
import springboot.infrastructure.security.persistence.entity.AuthRoleJpaEntity;
import springboot.infrastructure.security.persistence.entity.AuthUserJpaEntity;
import springboot.infrastructure.security.persistence.mapper.AuthPersistenceMapper;
import springboot.infrastructure.security.persistence.repository.AuthRoleJpaRepository;
import springboot.infrastructure.security.persistence.repository.AuthUserJpaRepository;

public class AuthUserRepositoryAdapter implements AuthUserRepository {
    private final AuthUserJpaRepository users;
    private final AuthRoleJpaRepository roles;

    public AuthUserRepositoryAdapter(AuthUserJpaRepository users, AuthRoleJpaRepository roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    public AuthUser save(AuthUser user) {
        Set<UUID> roleIds = user.roles().stream().map(role -> role.id()).collect(Collectors.toSet());
        Set<AuthRoleJpaEntity> managedRoles = roles.findAllById(roleIds).stream().collect(Collectors.toSet());
        if (managedRoles.size() != roleIds.size()) {
            throw new IllegalStateException("Uno o más roles asignados ya no existen");
        }
        AuthUserJpaEntity saved = users.save(AuthPersistenceMapper.toJpa(user, managedRoles));
        return AuthPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<AuthUser> findById(UUID id) {
        return users.findById(id).map(AuthPersistenceMapper::toDomain);
    }

    @Override
    public Optional<AuthUser> findByEmail(String email) {
        return users.findByEmailIgnoreCase(email).map(AuthPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.existsByEmailIgnoreCase(email);
    }
}
