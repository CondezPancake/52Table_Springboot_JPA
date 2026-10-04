package springboot.domain.patientcontact.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.patientcontact.event.PatientContactRegisteredEvent;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;

class PatientContactTest {
    @Test void shouldRegisterCreatedEvent() {
        PatientContact aggregate = PatientContact.register(
                ContactId.generate(),
                PatientId.generate(),
                true,
                false,
                RelationshipTypeId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        PatientContactRegisteredEvent event = assertInstanceOf(PatientContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
