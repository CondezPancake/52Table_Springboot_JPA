package springboot.infrastructure.phonecontact.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePhoneContactRequest(
        @NotNull(message = "contactId is required")
        UUID contactId,

        @Size(max = 30, message = "phone must have at most 30 characters")
        String phone,

        @NotNull(message = "notes is required")
        String notes
) {
}
