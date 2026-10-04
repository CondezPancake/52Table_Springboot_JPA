package springboot.infrastructure.professionaltype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.professionaltype.model.aggregate.ProfessionalType;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import springboot.domain.professionaltype.port.repository.ProfessionalTypeRepository;
import springboot.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;
import springboot.infrastructure.professionaltype.adapters.out.persistence.mappers.ProfessionalTypePersistenceMapper;

public class ProfessionalTypeRepositoryAdapter implements ProfessionalTypeRepository {
    private final ProfessionalTypeJpaRepository jpaRepository;
    private final ProfessionalTypePersistenceMapper mapper;
    public ProfessionalTypeRepositoryAdapter(ProfessionalTypeJpaRepository jpaRepository, ProfessionalTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ProfessionalType save(ProfessionalType aggregate) {
        ProfessionalTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ProfessionalType> findById(ProfessionalTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ProfessionalType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ProfessionalType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
