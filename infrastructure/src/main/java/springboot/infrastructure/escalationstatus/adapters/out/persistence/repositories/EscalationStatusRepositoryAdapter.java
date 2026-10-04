package springboot.infrastructure.escalationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.escalationstatus.model.aggregate.EscalationStatus;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;
import springboot.domain.escalationstatus.port.repository.EscalationStatusRepository;
import springboot.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;
import springboot.infrastructure.escalationstatus.adapters.out.persistence.mappers.EscalationStatusPersistenceMapper;

public class EscalationStatusRepositoryAdapter implements EscalationStatusRepository {
    private final EscalationStatusJpaRepository jpaRepository;
    private final EscalationStatusPersistenceMapper mapper;
    public EscalationStatusRepositoryAdapter(EscalationStatusJpaRepository jpaRepository, EscalationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public EscalationStatus save(EscalationStatus aggregate) {
        EscalationStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<EscalationStatus> findById(EscalationStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<EscalationStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(EscalationStatus aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
