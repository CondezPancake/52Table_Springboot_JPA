package springboot.domain.treatmentgoal.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.treatmentgoal.event.TreatmentGoalRegisteredEvent;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;

class TreatmentGoalTest {
    @Test void shouldRegisterCreatedEvent() {
        TreatmentGoal aggregate = TreatmentGoal.register(
                TreatmentPlanId.generate(),
                "Reduce symptoms",
                java.time.LocalDate.of(2026, 6, 10),
                java.time.LocalDateTime.of(2026, 6, 10, 9, 0),
                "Progress notes",
                TreatmentGoalStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        TreatmentGoalRegisteredEvent event = assertInstanceOf(TreatmentGoalRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
