package springboot.infrastructure.emailcontact.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateEmailContactRequest(
        @NotNull(message = "contactId is required")
        UUID contactId,

        @NotNull(message = "email is required")
        @Size(max = 150, message = "email must have at most 150 characters")
        String email,

        @NotNull(message = "notes is required")
        String notes
) {
}
