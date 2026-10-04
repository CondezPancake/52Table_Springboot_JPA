package springboot.infrastructure.consenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.consenttype.model.aggregate.ConsentType;
import springboot.domain.consenttype.model.valueobject.ConsentTypeId;
import springboot.domain.consenttype.port.repository.ConsentTypeRepository;
import springboot.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;
import springboot.infrastructure.consenttype.adapters.out.persistence.mappers.ConsentTypePersistenceMapper;

public class ConsentTypeRepositoryAdapter implements ConsentTypeRepository {
    private final ConsentTypeJpaRepository jpaRepository;
    private final ConsentTypePersistenceMapper mapper;
    public ConsentTypeRepositoryAdapter(ConsentTypeJpaRepository jpaRepository, ConsentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ConsentType save(ConsentType aggregate) {
        ConsentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ConsentType> findById(ConsentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ConsentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(ConsentType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
