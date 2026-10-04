package springboot.domain.clinicalrecordstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.clinicalrecordstatus.event.ClinicalRecordStatusRegisteredEvent;


class ClinicalRecordStatusTest {
    @Test void shouldRegisterCreatedEvent() {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register(
                "OPEN",
                "Open");
        assertEquals(1, aggregate.domainEvents().size());
        ClinicalRecordStatusRegisteredEvent event = assertInstanceOf(ClinicalRecordStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
