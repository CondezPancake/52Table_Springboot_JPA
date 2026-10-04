package springboot.infrastructure.country.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCountryRequest(
        @NotNull(message = "nameCountry is required")
        @Size(max = 50, message = "nameCountry must have at most 50 characters")
        String nameCountry,

        @NotNull(message = "codeCountry is required")
        @Size(max = 10, message = "codeCountry must have at most 10 characters")
        String codeCountry,

        @NotNull(message = "description is required")
        @Size(max = 100, message = "description must have at most 100 characters")
        String description,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "telephonePrefix is required")
        @Size(max = 5, message = "telephonePrefix must have at most 5 characters")
        String telephonePrefix
) {
}
