package springboot.domain.assessmenttype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.assessmenttype.event.AssessmentTypeRegisteredEvent;


class AssessmentTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        AssessmentType aggregate = AssessmentType.register(
                "INITIAL",
                "Initial assessment",
                true,
                "Initial clinical assessment");
        assertEquals(1, aggregate.domainEvents().size());
        AssessmentTypeRegisteredEvent event = assertInstanceOf(AssessmentTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
