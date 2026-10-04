package springboot.infrastructure.patientallergy.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePatientAllergyRequest(
        @NotNull(message = "patientId is required")
        UUID patientId,

        @NotNull(message = "substance is required")
        @Size(max = 200, message = "substance must have at most 200 characters")
        String substance,

        String reaction,

        @NotNull(message = "severity is required")
        @Size(max = 20, message = "severity must have at most 20 characters")
        String severity,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "recordedAt is required")
        LocalDateTime recordedAt,

        @NotNull(message = "recordedBy is required")
        UUID recordedBy
) {
}
