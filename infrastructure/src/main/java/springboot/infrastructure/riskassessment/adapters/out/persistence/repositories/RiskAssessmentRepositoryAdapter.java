package springboot.infrastructure.riskassessment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.riskassessment.model.aggregate.RiskAssessment;
import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;
import springboot.domain.riskassessment.port.repository.RiskAssessmentRepository;
import springboot.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;
import springboot.infrastructure.riskassessment.adapters.out.persistence.mappers.RiskAssessmentPersistenceMapper;

public class RiskAssessmentRepositoryAdapter implements RiskAssessmentRepository {
    private final RiskAssessmentJpaRepository jpaRepository;
    private final RiskAssessmentPersistenceMapper mapper;
    public RiskAssessmentRepositoryAdapter(RiskAssessmentJpaRepository jpaRepository, RiskAssessmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public RiskAssessment save(RiskAssessment aggregate) {
        RiskAssessmentJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<RiskAssessment> findById(RiskAssessmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<RiskAssessment> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(RiskAssessment aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
