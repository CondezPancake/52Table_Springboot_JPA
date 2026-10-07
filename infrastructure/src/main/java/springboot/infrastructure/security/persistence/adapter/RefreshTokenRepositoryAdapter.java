package springboot.infrastructure.security.persistence.adapter;

import java.util.Optional;

import springboot.domain.security.model.RefreshToken;
import springboot.domain.security.port.RefreshTokenRepository;
import springboot.infrastructure.security.persistence.mapper.AuthPersistenceMapper;
import springboot.infrastructure.security.persistence.repository.RefreshTokenJpaRepository;

public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository repository;

    public RefreshTokenRepositoryAdapter(RefreshTokenJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RefreshToken save(RefreshToken token) {
        return AuthPersistenceMapper.toDomain(repository.save(AuthPersistenceMapper.toJpa(token)));
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token).map(AuthPersistenceMapper::toDomain);
    }
}
