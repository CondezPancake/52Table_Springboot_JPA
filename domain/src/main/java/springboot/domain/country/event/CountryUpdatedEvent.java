package springboot.domain.country.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.country.model.valueobject.CountryId;


public record CountryUpdatedEvent(
        CountryId id,
        String nameCountry,
        String codeCountry,
        String description,
        boolean active,
        String telephonePrefix,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CountryUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameCountry, "nameCountry must not be null");
        Objects.requireNonNull(codeCountry, "codeCountry must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(telephonePrefix, "telephonePrefix must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
