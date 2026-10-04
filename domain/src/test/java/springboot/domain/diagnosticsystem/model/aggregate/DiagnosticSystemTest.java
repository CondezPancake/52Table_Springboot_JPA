package springboot.domain.diagnosticsystem.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.diagnosticsystem.event.DiagnosticSystemRegisteredEvent;


class DiagnosticSystemTest {
    @Test void shouldRegisterCreatedEvent() {
        DiagnosticSystem aggregate = DiagnosticSystem.register(
                "DSM",
                "Diagnostic and Statistical Manual",
                true,
                "5-TR");
        assertEquals(1, aggregate.domainEvents().size());
        DiagnosticSystemRegisteredEvent event = assertInstanceOf(DiagnosticSystemRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
