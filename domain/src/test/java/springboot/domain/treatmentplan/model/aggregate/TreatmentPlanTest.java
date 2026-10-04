package springboot.domain.treatmentplan.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.treatmentplan.event.TreatmentPlanRegisteredEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

class TreatmentPlanTest {
    @Test void shouldRegisterCreatedEvent() {
        TreatmentPlan aggregate = TreatmentPlan.register(
                EncounterId.generate(),
                ProfessionalId.generate(),
                "Initial treatment plan",
                "Treatment plan description",
                java.time.LocalDate.of(2026, 1, 10),
                java.time.LocalDate.of(2026, 6, 10),
                TreatmentStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        TreatmentPlanRegisteredEvent event = assertInstanceOf(TreatmentPlanRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
