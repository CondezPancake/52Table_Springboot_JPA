package springboot.domain.study.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.study.event.StudyRegisteredEvent;


class StudyTest {
    @Test void shouldRegisterCreatedEvent() {
        Study aggregate = Study.register(
                "Clinical Psychology");
        assertEquals(1, aggregate.domainEvents().size());
        StudyRegisteredEvent event = assertInstanceOf(StudyRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
