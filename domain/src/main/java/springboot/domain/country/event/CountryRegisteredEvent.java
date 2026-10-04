package springboot.domain.country.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.country.model.valueobject.CountryId;

public record CountryRegisteredEvent(
        CountryId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CountryRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
