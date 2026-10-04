package springboot.application.treatmentgoal.dto;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

public record TreatmentGoalResponse(
        UUID id,
        UUID treatmentPlanId,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        UUID treatmentGoalStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
