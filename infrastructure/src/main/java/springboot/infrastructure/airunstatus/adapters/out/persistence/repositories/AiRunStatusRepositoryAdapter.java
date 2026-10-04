package springboot.infrastructure.airunstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.airunstatus.model.aggregate.AiRunStatus;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;
import springboot.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;
import springboot.infrastructure.airunstatus.adapters.out.persistence.mappers.AiRunStatusPersistenceMapper;

public class AiRunStatusRepositoryAdapter implements AiRunStatusRepository {
    private final AiRunStatusJpaRepository jpaRepository;
    private final AiRunStatusPersistenceMapper mapper;
    public AiRunStatusRepositoryAdapter(AiRunStatusJpaRepository jpaRepository, AiRunStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public AiRunStatus save(AiRunStatus aggregate) {
        AiRunStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<AiRunStatus> findById(AiRunStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<AiRunStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(AiRunStatus aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
