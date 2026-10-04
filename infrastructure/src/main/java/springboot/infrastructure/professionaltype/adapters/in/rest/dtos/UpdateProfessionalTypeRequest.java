package springboot.infrastructure.professionaltype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProfessionalTypeRequest(
        @NotNull(message = "name is required")
        @Size(max = 40, message = "name must have at most 40 characters")
        String name
) {
}
