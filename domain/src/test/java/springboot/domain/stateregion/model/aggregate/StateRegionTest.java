package springboot.domain.stateregion.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.stateregion.event.StateRegionRegisteredEvent;
import springboot.domain.country.model.valueobject.CountryId;

class StateRegionTest {
    @Test void shouldRegisterCreatedEvent() {
        StateRegion aggregate = StateRegion.register(
                "Cundinamarca",
                "CUN",
                "Central region",
                true,
                CountryId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        StateRegionRegisteredEvent event = assertInstanceOf(StateRegionRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
