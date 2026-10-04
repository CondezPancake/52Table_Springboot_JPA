package springboot.domain.contact.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record ContactUpdatedEvent(
        ContactId id,
        String fullName,
        String email,
        String notes,
        CityMunicipalityId cityId,
        ProfessionalId createdBy,
        ProfessionalId updatedBy,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
        Objects.requireNonNull(cityId, "cityId must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
