package springboot.infrastructure.patientcontact.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePatientContactRequest(
        @NotNull(message = "contactId is required")
        UUID contactId,

        @NotNull(message = "patientId is required")
        UUID patientId,

        @NotNull(message = "primaryContact is required")
        Boolean primaryContact,

        @NotNull(message = "emergencyContact is required")
        Boolean emergencyContact,

        @NotNull(message = "relationshipTypeId is required")
        UUID relationshipTypeId
) {
}
