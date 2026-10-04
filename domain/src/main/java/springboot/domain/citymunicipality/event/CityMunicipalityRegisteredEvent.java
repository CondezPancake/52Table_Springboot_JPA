package springboot.domain.citymunicipality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public record CityMunicipalityRegisteredEvent(
        CityMunicipalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CityMunicipalityRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
