package springboot.infrastructure.relationshiptype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.relationshiptype.model.aggregate.RelationshipType;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;
import springboot.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;
import springboot.infrastructure.relationshiptype.adapters.out.persistence.mappers.RelationshipTypePersistenceMapper;

public class RelationshipTypeRepositoryAdapter implements RelationshipTypeRepository {
    private final RelationshipTypeJpaRepository jpaRepository;
    private final RelationshipTypePersistenceMapper mapper;
    public RelationshipTypeRepositoryAdapter(RelationshipTypeJpaRepository jpaRepository, RelationshipTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public RelationshipType save(RelationshipType aggregate) {
        RelationshipTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<RelationshipType> findById(RelationshipTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<RelationshipType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(RelationshipType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
