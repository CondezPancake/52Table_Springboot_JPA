package springboot.domain.relationshiptype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.relationshiptype.event.RelationshipTypeRegisteredEvent;


class RelationshipTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        RelationshipType aggregate = RelationshipType.register(
                "Parent");
        assertEquals(1, aggregate.domainEvents().size());
        RelationshipTypeRegisteredEvent event = assertInstanceOf(RelationshipTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
