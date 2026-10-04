package springboot.infrastructure.citymunicipality.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCityMunicipalityRequest(
        @NotNull(message = "nameCity is required")
        @Size(max = 50, message = "nameCity must have at most 50 characters")
        String nameCity,

        @NotNull(message = "codeCity is required")
        @Size(max = 10, message = "codeCity must have at most 10 characters")
        String codeCity,

        @NotNull(message = "description is required")
        @Size(max = 100, message = "description must have at most 100 characters")
        String description,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "regionId is required")
        UUID regionId
) {
}
