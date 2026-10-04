package springboot.domain.patient.event;

import java.time.LocalDateTime;
import java.util.Objects;
import java.time.LocalDate;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record PatientUpdatedEvent(
        PatientId id,
        DocumentTypeId documentTypeId,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        GenderId biologicalSexId,
        GenderId genderIdentityId,
        String email,
        String phone,
        String address,
        boolean active,
        ProfessionalId createdBy,
        ProfessionalId updatedBy,
        CityMunicipalityId cityId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PatientUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(documentTypeId, "documentTypeId must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(birthDate, "birthDate must not be null");
        Objects.requireNonNull(biologicalSexId, "biologicalSexId must not be null");
        Objects.requireNonNull(genderIdentityId, "genderIdentityId must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(phone, "phone must not be null");
        Objects.requireNonNull(address, "address must not be null");
        Objects.requireNonNull(cityId, "cityId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
