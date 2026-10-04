package springboot.infrastructure.clinicalnote.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateClinicalNoteRequest(
        @NotNull(message = "encounterId is required")
        UUID encounterId,

        @NotNull(message = "professionalId is required")
        UUID professionalId,

        @NotNull(message = "subjective is required")
        String subjective,

        @NotNull(message = "objective is required")
        String objective,

        @NotNull(message = "assessment is required")
        String assessment,

        @NotNull(message = "plan is required")
        String plan,

        @NotNull(message = "additionalNotes is required")
        String additionalNotes,

        @NotNull(message = "signedAt is required")
        LocalDateTime signedAt
) {
}
