package springboot.infrastructure.sendertype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.sendertype.model.aggregate.SenderType;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;
import springboot.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;
import springboot.infrastructure.sendertype.adapters.out.persistence.mappers.SenderTypePersistenceMapper;

public class SenderTypeRepositoryAdapter implements SenderTypeRepository {
    private final SenderTypeJpaRepository jpaRepository;
    private final SenderTypePersistenceMapper mapper;
    public SenderTypeRepositoryAdapter(SenderTypeJpaRepository jpaRepository, SenderTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public SenderType save(SenderType aggregate) {
        SenderTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<SenderType> findById(SenderTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<SenderType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(SenderType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
