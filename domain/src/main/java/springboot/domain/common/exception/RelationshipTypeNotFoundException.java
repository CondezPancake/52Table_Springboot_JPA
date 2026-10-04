package springboot.domain.common.exception;

import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class RelationshipTypeNotFoundException extends RuntimeException {
    public RelationshipTypeNotFoundException(RelationshipTypeId id) {
        super("RelationshipType not found with id: " + id.value());
    }
}
