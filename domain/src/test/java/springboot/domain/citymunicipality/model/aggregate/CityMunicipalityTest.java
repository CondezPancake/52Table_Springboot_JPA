package springboot.domain.citymunicipality.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.citymunicipality.event.CityMunicipalityRegisteredEvent;
import springboot.domain.stateregion.model.valueobject.StateRegionId;

class CityMunicipalityTest {
    @Test void shouldRegisterCreatedEvent() {
        CityMunicipality aggregate = CityMunicipality.register(
                "Bogota",
                "BOG",
                "Capital district",
                true,
                StateRegionId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        CityMunicipalityRegisteredEvent event = assertInstanceOf(CityMunicipalityRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
