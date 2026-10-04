package springboot.domain.contact.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.contact.event.ContactRegisteredEvent;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class ContactTest {
    @Test void shouldRegisterCreatedEvent() {
        Contact aggregate = Contact.register(
                "Emergency Contact",
                "contact@example.com",
                "Primary contact",
                CityMunicipalityId.generate(),
                ProfessionalId.generate(),
                null);
        assertEquals(1, aggregate.domainEvents().size());
        ContactRegisteredEvent event = assertInstanceOf(ContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
