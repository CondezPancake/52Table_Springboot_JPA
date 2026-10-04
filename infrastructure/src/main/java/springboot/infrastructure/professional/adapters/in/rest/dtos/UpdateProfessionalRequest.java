package springboot.infrastructure.professional.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProfessionalRequest(
        @NotNull(message = "documentTypeId is required")
        UUID documentTypeId,

        @NotNull(message = "documentNumber is required")
        @Size(max = 30, message = "documentNumber must have at most 30 characters")
        String documentNumber,

        @NotNull(message = "firstName is required")
        @Size(max = 60, message = "firstName must have at most 60 characters")
        String firstName,

        @NotNull(message = "lastName is required")
        @Size(max = 60, message = "lastName must have at most 60 characters")
        String lastName,

        @NotNull(message = "professionalTypeId is required")
        UUID professionalTypeId,

        @NotNull(message = "licenseNumber is required")
        @Size(max = 100, message = "licenseNumber must have at most 100 characters")
        String licenseNumber,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "cityId is required")
        UUID cityId
) {
}
