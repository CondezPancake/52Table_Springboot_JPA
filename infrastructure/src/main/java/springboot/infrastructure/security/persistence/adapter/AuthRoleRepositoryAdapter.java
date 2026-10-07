package springboot.infrastructure.security.persistence.adapter;

import java.util.Optional;

import springboot.domain.security.model.AuthRole;
import springboot.domain.security.port.AuthRoleRepository;
import springboot.infrastructure.security.persistence.mapper.AuthPersistenceMapper;
import springboot.infrastructure.security.persistence.repository.AuthRoleJpaRepository;

public class AuthRoleRepositoryAdapter implements AuthRoleRepository {
    private final AuthRoleJpaRepository repository;

    public AuthRoleRepositoryAdapter(AuthRoleJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<AuthRole> findByAuthority(String authority) {
        return repository.findByAuthorityIgnoreCase(authority).map(AuthPersistenceMapper::toDomain);
    }

    @Override
    public Optional<AuthRole> findByName(String name) {
        return repository.findByNameIgnoreCase(name).map(AuthPersistenceMapper::toDomain);
    }
}
