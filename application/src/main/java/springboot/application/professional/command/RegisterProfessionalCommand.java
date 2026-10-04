package springboot.application.professional.command;

import java.util.Objects;


import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record RegisterProfessionalCommand(
        DocumentTypeId documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        ProfessionalTypeId professionalTypeId,
        String licenseNumber,
        boolean active,
        CityMunicipalityId cityId
) {
    public RegisterProfessionalCommand {
        Objects.requireNonNull(documentTypeId, "documentTypeId must not be null");
        Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        Objects.requireNonNull(firstName, "firstName must not be null");
        Objects.requireNonNull(lastName, "lastName must not be null");
        Objects.requireNonNull(professionalTypeId, "professionalTypeId must not be null");
        Objects.requireNonNull(licenseNumber, "licenseNumber must not be null");
        Objects.requireNonNull(cityId, "cityId must not be null");
    }
}
