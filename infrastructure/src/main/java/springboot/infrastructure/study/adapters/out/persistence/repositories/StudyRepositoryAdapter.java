package springboot.infrastructure.study.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.study.model.aggregate.Study;
import springboot.domain.study.model.valueobject.StudyId;
import springboot.domain.study.port.repository.StudyRepository;
import springboot.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;
import springboot.infrastructure.study.adapters.out.persistence.mappers.StudyPersistenceMapper;

public class StudyRepositoryAdapter implements StudyRepository {
    private final StudyJpaRepository jpaRepository;
    private final StudyPersistenceMapper mapper;
    public StudyRepositoryAdapter(StudyJpaRepository jpaRepository, StudyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Study save(Study aggregate) {
        StudyJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Study> findById(StudyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Study> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(Study aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
