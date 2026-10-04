package springboot.infrastructure.professional.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.professional.model.aggregate.Professional;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.professional.port.repository.ProfessionalRepository;
import springboot.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;
import springboot.infrastructure.professional.adapters.out.persistence.mappers.ProfessionalPersistenceMapper;

public class ProfessionalRepositoryAdapter implements ProfessionalRepository {
    private final ProfessionalJpaRepository jpaRepository;
    private final ProfessionalPersistenceMapper mapper;
    public ProfessionalRepositoryAdapter(ProfessionalJpaRepository jpaRepository, ProfessionalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Professional save(Professional aggregate) {
        ProfessionalJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Professional> findById(ProfessionalId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Professional> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(Professional aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
