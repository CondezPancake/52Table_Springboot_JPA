package springboot.domain.documenttype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.documenttype.event.DocumentTypeRegisteredEvent;


class DocumentTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        DocumentType aggregate = DocumentType.register(
                "CC",
                "Citizenship card",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        DocumentTypeRegisteredEvent event = assertInstanceOf(DocumentTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
