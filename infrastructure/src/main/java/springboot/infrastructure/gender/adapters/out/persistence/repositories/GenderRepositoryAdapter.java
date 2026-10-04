package springboot.infrastructure.gender.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.gender.model.aggregate.Gender;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.gender.port.repository.GenderRepository;
import springboot.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;
import springboot.infrastructure.gender.adapters.out.persistence.mappers.GenderPersistenceMapper;

public class GenderRepositoryAdapter implements GenderRepository {
    private final GenderJpaRepository jpaRepository;
    private final GenderPersistenceMapper mapper;
    public GenderRepositoryAdapter(GenderJpaRepository jpaRepository, GenderPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Gender save(Gender aggregate) {
        GenderJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Gender> findById(GenderId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Gender> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(Gender aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
