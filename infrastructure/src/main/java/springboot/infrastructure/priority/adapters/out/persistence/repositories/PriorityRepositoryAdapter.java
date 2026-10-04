package springboot.infrastructure.priority.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.priority.model.aggregate.Priority;
import springboot.domain.priority.model.valueobject.PriorityId;
import springboot.domain.priority.port.repository.PriorityRepository;
import springboot.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;
import springboot.infrastructure.priority.adapters.out.persistence.mappers.PriorityPersistenceMapper;

public class PriorityRepositoryAdapter implements PriorityRepository {
    private final PriorityJpaRepository jpaRepository;
    private final PriorityPersistenceMapper mapper;
    public PriorityRepositoryAdapter(PriorityJpaRepository jpaRepository, PriorityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Priority save(Priority aggregate) {
        PriorityJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Priority> findById(PriorityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Priority> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(Priority aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
