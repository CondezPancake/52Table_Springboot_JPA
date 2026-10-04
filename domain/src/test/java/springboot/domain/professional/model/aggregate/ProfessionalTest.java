package springboot.domain.professional.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.professional.event.ProfessionalRegisteredEvent;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

class ProfessionalTest {
    @Test void shouldRegisterCreatedEvent() {
        Professional aggregate = Professional.register(
                DocumentTypeId.generate(),
                "123456789",
                "Ana",
                "Ramirez",
                ProfessionalTypeId.generate(),
                "PSY-12345",
                true,
                CityMunicipalityId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ProfessionalRegisteredEvent event = assertInstanceOf(ProfessionalRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
