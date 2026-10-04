package springboot.application.treatmentgoal.command;

import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;

import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public record UpdateTreatmentGoalCommand(
        TreatmentGoalId id,
        TreatmentPlanId treatmentPlanId,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        TreatmentGoalStatusId treatmentGoalStatusId
) {
    public UpdateTreatmentGoalCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(treatmentPlanId, "treatmentPlanId must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(targetDate, "targetDate must not be null");
        Objects.requireNonNull(completedAt, "completedAt must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
        Objects.requireNonNull(treatmentGoalStatusId, "treatmentGoalStatusId must not be null");
    }
}
