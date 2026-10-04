package springboot.domain.clinicalrecord.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.clinicalrecord.event.ClinicalRecordRegisteredEvent;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class ClinicalRecordTest {
    @Test void shouldRegisterCreatedEvent() {
        ClinicalRecord aggregate = ClinicalRecord.register(
                PatientId.generate(),
                java.time.LocalDateTime.of(2026, 1, 10, 8, 0),
                "CR-2026-0001",
                java.time.LocalDateTime.of(2026, 1, 10, 8, 0),
                java.time.LocalDateTime.of(2026, 1, 10, 9, 0),
                ClinicalRecordStatusId.generate(),
                ProfessionalId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ClinicalRecordRegisteredEvent event = assertInstanceOf(ClinicalRecordRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
