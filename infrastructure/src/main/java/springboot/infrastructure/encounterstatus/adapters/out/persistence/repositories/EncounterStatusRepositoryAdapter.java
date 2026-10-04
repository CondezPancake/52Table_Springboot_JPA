package springboot.infrastructure.encounterstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.encounterstatus.model.aggregate.EncounterStatus;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;
import springboot.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;
import springboot.infrastructure.encounterstatus.adapters.out.persistence.mappers.EncounterStatusPersistenceMapper;

public class EncounterStatusRepositoryAdapter implements EncounterStatusRepository {
    private final EncounterStatusJpaRepository jpaRepository;
    private final EncounterStatusPersistenceMapper mapper;
    public EncounterStatusRepositoryAdapter(EncounterStatusJpaRepository jpaRepository, EncounterStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public EncounterStatus save(EncounterStatus aggregate) {
        EncounterStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<EncounterStatus> findById(EncounterStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<EncounterStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(EncounterStatus aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
