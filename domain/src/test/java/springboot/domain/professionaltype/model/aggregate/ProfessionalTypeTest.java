package springboot.domain.professionaltype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.professionaltype.event.ProfessionalTypeRegisteredEvent;


class ProfessionalTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        ProfessionalType aggregate = ProfessionalType.register(
                "Psychologist");
        assertEquals(1, aggregate.domainEvents().size());
        ProfessionalTypeRegisteredEvent event = assertInstanceOf(ProfessionalTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
