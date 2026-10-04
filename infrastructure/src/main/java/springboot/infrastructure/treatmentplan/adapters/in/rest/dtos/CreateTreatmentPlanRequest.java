package springboot.infrastructure.treatmentplan.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTreatmentPlanRequest(
        @NotNull(message = "encounterId is required")
        UUID encounterId,

        @NotNull(message = "professionalId is required")
        UUID professionalId,

        @NotNull(message = "title is required")
        @Size(max = 200, message = "title must have at most 200 characters")
        String title,

        @NotNull(message = "description is required")
        String description,

        @NotNull(message = "startDate is required")
        LocalDate startDate,

        @NotNull(message = "endDate is required")
        LocalDate endDate,

        @NotNull(message = "treatmentStatusId is required")
        UUID treatmentStatusId
) {
}
