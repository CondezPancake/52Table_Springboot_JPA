package springboot.infrastructure.professionalstudy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import springboot.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;
import springboot.infrastructure.professionalstudy.adapters.out.persistence.mappers.ProfessionalStudyPersistenceMapper;

public class ProfessionalStudyRepositoryAdapter implements ProfessionalStudyRepository {
    private final ProfessionalStudyJpaRepository jpaRepository;
    private final ProfessionalStudyPersistenceMapper mapper;
    public ProfessionalStudyRepositoryAdapter(ProfessionalStudyJpaRepository jpaRepository, ProfessionalStudyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ProfessionalStudy save(ProfessionalStudy aggregate) {
        ProfessionalStudyJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ProfessionalStudy> findById(ProfessionalStudyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ProfessionalStudy> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ProfessionalStudy aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
