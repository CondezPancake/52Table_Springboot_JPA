package springboot.infrastructure.encounter.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.encounter.model.aggregate.Encounter;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.encounter.port.repository.EncounterRepository;
import springboot.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;
import springboot.infrastructure.encounter.adapters.out.persistence.mappers.EncounterPersistenceMapper;

public class EncounterRepositoryAdapter implements EncounterRepository {
    private final EncounterJpaRepository jpaRepository;
    private final EncounterPersistenceMapper mapper;
    public EncounterRepositoryAdapter(EncounterJpaRepository jpaRepository, EncounterPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Encounter save(Encounter aggregate) {
        EncounterJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Encounter> findById(EncounterId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Encounter> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(Encounter aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
