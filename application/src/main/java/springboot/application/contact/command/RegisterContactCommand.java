package springboot.application.contact.command;

import java.util.Objects;


import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record RegisterContactCommand(
        String fullName,
        String email,
        String notes,
        CityMunicipalityId cityId,
        ProfessionalId createdBy,
        ProfessionalId updatedBy
) {
    public RegisterContactCommand {
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
        Objects.requireNonNull(cityId, "cityId must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
    }
}
