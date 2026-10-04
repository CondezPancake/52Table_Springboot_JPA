package springboot.domain.relationshiptype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.relationshiptype.model.aggregate.RelationshipType;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public interface RelationshipTypeRepository {
    RelationshipType save(RelationshipType aggregate);
    Optional<RelationshipType> findById(RelationshipTypeId id);
    List<RelationshipType> findAll();

    void delete(RelationshipType aggregate);
}
