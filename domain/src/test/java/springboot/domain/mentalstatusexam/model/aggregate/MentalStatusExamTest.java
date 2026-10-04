package springboot.domain.mentalstatusexam.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.mentalstatusexam.event.MentalStatusExamRegisteredEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class MentalStatusExamTest {
    @Test void shouldRegisterCreatedEvent() {
        MentalStatusExam aggregate = MentalStatusExam.register(
                EncounterId.generate(),
                "Appropriate",
                "Cooperative",
                "Open",
                "Alert",
                "Oriented",
                "Sustained",
                "Intact",
                "Clear",
                "Stable",
                "Congruent",
                "Logical",
                "Appropriate",
                "No alterations",
                "Preserved",
                "Present",
                "Normal",
                "No additional findings",
                ProfessionalId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        MentalStatusExamRegisteredEvent event = assertInstanceOf(MentalStatusExamRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
