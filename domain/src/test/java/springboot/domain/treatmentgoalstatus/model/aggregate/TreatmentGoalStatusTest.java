package springboot.domain.treatmentgoalstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.treatmentgoalstatus.event.TreatmentGoalStatusRegisteredEvent;


class TreatmentGoalStatusTest {
    @Test void shouldRegisterCreatedEvent() {
        TreatmentGoalStatus aggregate = TreatmentGoalStatus.register(
                "IN_PROGRESS",
                "In progress",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        TreatmentGoalStatusRegisteredEvent event = assertInstanceOf(TreatmentGoalStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
