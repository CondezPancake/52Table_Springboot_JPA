package springboot.domain.encounter.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.encounter.event.EncounterRegisteredEvent;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class EncounterTest {
    @Test void shouldRegisterCreatedEvent() {
        Encounter aggregate = Encounter.register(
                ClinicalRecordId.generate(),
                ProfessionalId.generate(),
                EncounterTypeId.generate(),
                java.time.LocalDateTime.of(2026, 1, 10, 8, 0),
                java.time.LocalDateTime.of(2026, 1, 10, 9, 0),
                "Initial consultation",
                "Stable",
                EncounterModalityId.generate(),
                EncounterStatusId.generate(),
                ProfessionalId.generate(),
                ProfessionalId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        EncounterRegisteredEvent event = assertInstanceOf(EncounterRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
