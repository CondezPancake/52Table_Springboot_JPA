package springboot.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import springboot.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;
import springboot.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers.TreatmentGoalStatusPersistenceMapper;

public class TreatmentGoalStatusRepositoryAdapter implements TreatmentGoalStatusRepository {
    private final TreatmentGoalStatusJpaRepository jpaRepository;
    private final TreatmentGoalStatusPersistenceMapper mapper;
    public TreatmentGoalStatusRepositoryAdapter(TreatmentGoalStatusJpaRepository jpaRepository, TreatmentGoalStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public TreatmentGoalStatus save(TreatmentGoalStatus aggregate) {
        TreatmentGoalStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<TreatmentGoalStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(TreatmentGoalStatus aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
