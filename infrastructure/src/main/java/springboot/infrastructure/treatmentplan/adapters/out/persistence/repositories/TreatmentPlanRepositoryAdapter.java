package springboot.infrastructure.treatmentplan.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.treatmentplan.model.aggregate.TreatmentPlan;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import springboot.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;
import springboot.infrastructure.treatmentplan.adapters.out.persistence.mappers.TreatmentPlanPersistenceMapper;

public class TreatmentPlanRepositoryAdapter implements TreatmentPlanRepository {
    private final TreatmentPlanJpaRepository jpaRepository;
    private final TreatmentPlanPersistenceMapper mapper;
    public TreatmentPlanRepositoryAdapter(TreatmentPlanJpaRepository jpaRepository, TreatmentPlanPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public TreatmentPlan save(TreatmentPlan aggregate) {
        TreatmentPlanJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<TreatmentPlan> findById(TreatmentPlanId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<TreatmentPlan> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(TreatmentPlan aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
