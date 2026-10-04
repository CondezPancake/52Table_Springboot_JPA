package springboot.infrastructure.risklevel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.risklevel.model.aggregate.RiskLevel;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;
import springboot.domain.risklevel.port.repository.RiskLevelRepository;
import springboot.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;
import springboot.infrastructure.risklevel.adapters.out.persistence.mappers.RiskLevelPersistenceMapper;

public class RiskLevelRepositoryAdapter implements RiskLevelRepository {
    private final RiskLevelJpaRepository jpaRepository;
    private final RiskLevelPersistenceMapper mapper;
    public RiskLevelRepositoryAdapter(RiskLevelJpaRepository jpaRepository, RiskLevelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public RiskLevel save(RiskLevel aggregate) {
        RiskLevelJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<RiskLevel> findById(RiskLevelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<RiskLevel> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(RiskLevel aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
