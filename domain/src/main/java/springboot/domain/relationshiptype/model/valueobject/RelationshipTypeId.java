package springboot.domain.relationshiptype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record RelationshipTypeId(UUID value) {
    public RelationshipTypeId {
        Objects.requireNonNull(value, "RelationshipTypeId value must not be null");
    }

    public static RelationshipTypeId generate() {
        return new RelationshipTypeId(UUID.randomUUID());
    }
}
