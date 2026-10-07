package springboot.infrastructure.security.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.infrastructure.security.persistence.entity.AuthRoleJpaEntity;

public interface AuthRoleJpaRepository extends JpaRepository<AuthRoleJpaEntity, UUID> {
    Optional<AuthRoleJpaEntity> findByAuthorityIgnoreCase(String authority);
    Optional<AuthRoleJpaEntity> findByNameIgnoreCase(String name);
}
