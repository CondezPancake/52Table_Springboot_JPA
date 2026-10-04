package springboot.infrastructure.assessmenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.assessmenttype.model.aggregate.AssessmentType;
import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import springboot.domain.assessmenttype.port.repository.AssessmentTypeRepository;
import springboot.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;
import springboot.infrastructure.assessmenttype.adapters.out.persistence.mappers.AssessmentTypePersistenceMapper;

public class AssessmentTypeRepositoryAdapter implements AssessmentTypeRepository {
    private final AssessmentTypeJpaRepository jpaRepository;
    private final AssessmentTypePersistenceMapper mapper;
    public AssessmentTypeRepositoryAdapter(AssessmentTypeJpaRepository jpaRepository, AssessmentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public AssessmentType save(AssessmentType aggregate) {
        AssessmentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<AssessmentType> findById(AssessmentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<AssessmentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(AssessmentType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
