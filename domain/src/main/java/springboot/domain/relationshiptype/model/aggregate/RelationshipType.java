package springboot.domain.relationshiptype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.relationshiptype.event.RelationshipTypeRegisteredEvent;
import springboot.domain.relationshiptype.event.RelationshipTypeUpdatedEvent;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;


public class RelationshipType extends AggregateRoot {
    private final RelationshipTypeId id;
    private String description;


    private RelationshipType(
            RelationshipTypeId id,
            String description) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");

    }

    public static RelationshipType register(
            String description) {
        RelationshipTypeId id = RelationshipTypeId.generate();
        LocalDateTime occurredOn = LocalDateTime.now();
        RelationshipType aggregate = new RelationshipType(
                id,
                description);
        aggregate.recordEvent(new RelationshipTypeRegisteredEvent(id, occurredOn));
        return aggregate;
    }

    public static RelationshipType restore(
            RelationshipTypeId id,
            String description) {
        return new RelationshipType(
                id,
                description);
    }

    public void update(
            String description) {
        this.description = Objects.requireNonNull(description, "description must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new RelationshipTypeUpdatedEvent(
                        this.id,
                        this.description,
                        occurredOn));
    }

    public RelationshipTypeId id() {
        return id;
    }

    public String description() {
        return description;
    }


}
