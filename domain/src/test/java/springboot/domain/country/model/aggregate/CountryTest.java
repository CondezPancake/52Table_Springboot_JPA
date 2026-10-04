package springboot.domain.country.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.country.event.CountryRegisteredEvent;


class CountryTest {
    @Test void shouldRegisterCreatedEvent() {
        Country aggregate = Country.register(
                "Colombia",
                "CO",
                "Republic of Colombia",
                true,
                "+57");
        assertEquals(1, aggregate.domainEvents().size());
        CountryRegisteredEvent event = assertInstanceOf(CountryRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
