package springboot.infrastructure.stateregion.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateStateRegionRequest(
        @NotNull(message = "nameRegion is required")
        @Size(max = 50, message = "nameRegion must have at most 50 characters")
        String nameRegion,

        @NotNull(message = "codeRegion is required")
        @Size(max = 10, message = "codeRegion must have at most 10 characters")
        String codeRegion,

        @NotNull(message = "description is required")
        @Size(max = 100, message = "description must have at most 100 characters")
        String description,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "countryId is required")
        UUID countryId
) {
}
