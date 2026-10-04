package springboot.infrastructure.encountermodality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.encountermodality.model.aggregate.EncounterModality;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;
import springboot.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;
import springboot.infrastructure.encountermodality.adapters.out.persistence.mappers.EncounterModalityPersistenceMapper;

public class EncounterModalityRepositoryAdapter implements EncounterModalityRepository {
    private final EncounterModalityJpaRepository jpaRepository;
    private final EncounterModalityPersistenceMapper mapper;
    public EncounterModalityRepositoryAdapter(EncounterModalityJpaRepository jpaRepository, EncounterModalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public EncounterModality save(EncounterModality aggregate) {
        EncounterModalityJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<EncounterModality> findById(EncounterModalityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<EncounterModality> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(EncounterModality aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
