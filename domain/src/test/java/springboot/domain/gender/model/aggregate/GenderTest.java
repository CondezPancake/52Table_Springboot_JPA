package springboot.domain.gender.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.gender.event.GenderRegisteredEvent;


class GenderTest {
    @Test void shouldRegisterCreatedEvent() {
        Gender aggregate = Gender.register(
                "Female");
        assertEquals(1, aggregate.domainEvents().size());
        GenderRegisteredEvent event = assertInstanceOf(GenderRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
