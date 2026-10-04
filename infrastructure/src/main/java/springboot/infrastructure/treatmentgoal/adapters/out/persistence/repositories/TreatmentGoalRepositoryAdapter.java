package springboot.infrastructure.treatmentgoal.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import springboot.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;
import springboot.infrastructure.treatmentgoal.adapters.out.persistence.mappers.TreatmentGoalPersistenceMapper;

public class TreatmentGoalRepositoryAdapter implements TreatmentGoalRepository {
    private final TreatmentGoalJpaRepository jpaRepository;
    private final TreatmentGoalPersistenceMapper mapper;
    public TreatmentGoalRepositoryAdapter(TreatmentGoalJpaRepository jpaRepository, TreatmentGoalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public TreatmentGoal save(TreatmentGoal aggregate) {
        TreatmentGoalJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<TreatmentGoal> findById(TreatmentGoalId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<TreatmentGoal> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(TreatmentGoal aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
