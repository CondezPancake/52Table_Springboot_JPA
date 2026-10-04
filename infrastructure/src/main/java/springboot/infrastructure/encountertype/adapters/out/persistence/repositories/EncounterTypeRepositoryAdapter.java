package springboot.infrastructure.encountertype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.encountertype.model.aggregate.EncounterType;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;
import springboot.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;
import springboot.infrastructure.encountertype.adapters.out.persistence.mappers.EncounterTypePersistenceMapper;

public class EncounterTypeRepositoryAdapter implements EncounterTypeRepository {
    private final EncounterTypeJpaRepository jpaRepository;
    private final EncounterTypePersistenceMapper mapper;
    public EncounterTypeRepositoryAdapter(EncounterTypeJpaRepository jpaRepository, EncounterTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public EncounterType save(EncounterType aggregate) {
        EncounterTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<EncounterType> findById(EncounterTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<EncounterType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(EncounterType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
