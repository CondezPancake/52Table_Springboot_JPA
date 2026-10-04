package springboot.domain.riskassessment.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.riskassessment.event.RiskAssessmentRegisteredEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;

class RiskAssessmentTest {
    @Test void shouldRegisterCreatedEvent() {
        RiskAssessment aggregate = RiskAssessment.register(
                EncounterId.generate(),
                RiskLevelId.generate(),
                false,
                false,
                false,
                false,
                false,
                "No acute factors",
                "Family support",
                "Continue monitoring",
                "Stable",
                java.time.LocalDateTime.of(2026, 1, 10, 8, 0),
                ProfessionalId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        RiskAssessmentRegisteredEvent event = assertInstanceOf(RiskAssessmentRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
