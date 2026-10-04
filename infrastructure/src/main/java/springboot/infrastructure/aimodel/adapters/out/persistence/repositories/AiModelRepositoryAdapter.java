package springboot.infrastructure.aimodel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.aimodel.model.aggregate.AiModel;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.aimodel.port.repository.AiModelRepository;
import springboot.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;
import springboot.infrastructure.aimodel.adapters.out.persistence.mappers.AiModelPersistenceMapper;

public class AiModelRepositoryAdapter implements AiModelRepository {
    private final AiModelJpaRepository jpaRepository;
    private final AiModelPersistenceMapper mapper;
    public AiModelRepositoryAdapter(AiModelJpaRepository jpaRepository, AiModelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public AiModel save(AiModel aggregate) {
        AiModelJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<AiModel> findById(AiModelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<AiModel> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(AiModel aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
