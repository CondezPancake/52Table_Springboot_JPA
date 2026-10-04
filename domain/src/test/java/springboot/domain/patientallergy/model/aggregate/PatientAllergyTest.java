package springboot.domain.patientallergy.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.patientallergy.event.PatientAllergyRegisteredEvent;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class PatientAllergyTest {
    @Test void shouldRegisterCreatedEvent() {
        PatientAllergy aggregate = PatientAllergy.register(
                PatientId.generate(),
                "Penicillin",
                null,
                "HIGH",
                true,
                java.time.LocalDateTime.of(2026, 1, 10, 8, 0),
                ProfessionalId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        PatientAllergyRegisteredEvent event = assertInstanceOf(PatientAllergyRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
