package springboot.infrastructure.treatmentgoal.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTreatmentGoalRequest(
        @NotNull(message = "treatmentPlanId is required")
        UUID treatmentPlanId,

        @NotNull(message = "description is required")
        String description,

        @NotNull(message = "targetDate is required")
        LocalDate targetDate,

        @NotNull(message = "completedAt is required")
        LocalDateTime completedAt,

        @NotNull(message = "notes is required")
        String notes,

        @NotNull(message = "treatmentGoalStatusId is required")
        UUID treatmentGoalStatusId
) {
}
