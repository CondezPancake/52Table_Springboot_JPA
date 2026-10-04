package springboot.domain.clinicalnote.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.clinicalnote.event.ClinicalNoteRegisteredEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class ClinicalNoteTest {
    @Test void shouldRegisterCreatedEvent() {
        ClinicalNote aggregate = ClinicalNote.register(
                EncounterId.generate(),
                ProfessionalId.generate(),
                "Patient report",
                "Clinical observation",
                "Clinical assessment",
                "Follow-up plan",
                "No additional notes",
                java.time.LocalDateTime.of(2026, 1, 10, 9, 0));
        assertEquals(1, aggregate.domainEvents().size());
        ClinicalNoteRegisteredEvent event = assertInstanceOf(ClinicalNoteRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
