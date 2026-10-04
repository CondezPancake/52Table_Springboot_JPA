package springboot.infrastructure.contact.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateContactRequest(
        @NotNull(message = "fullName is required")
        @Size(max = 200, message = "fullName must have at most 200 characters")
        String fullName,

        @NotNull(message = "email is required")
        @Size(max = 150, message = "email must have at most 150 characters")
        String email,

        @NotNull(message = "notes is required")
        String notes,

        @NotNull(message = "cityId is required")
        UUID cityId,

        @NotNull(message = "createdBy is required")
        UUID createdBy,

        UUID updatedBy
) {
}
