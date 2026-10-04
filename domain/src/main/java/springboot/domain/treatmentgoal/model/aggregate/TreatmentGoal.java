package springboot.domain.treatmentgoal.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.time.LocalDate;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.treatmentgoal.event.TreatmentGoalRegisteredEvent;
import springboot.domain.treatmentgoal.event.TreatmentGoalUpdatedEvent;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public class TreatmentGoal extends AggregateRoot {
    private final TreatmentGoalId id;
    private TreatmentPlanId treatmentPlanId;
    private String description;
    private LocalDate targetDate;
    private LocalDateTime completedAt;
    private String notes;
    private TreatmentGoalStatusId treatmentGoalStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentGoal(
            TreatmentGoalId id,
            TreatmentPlanId treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            TreatmentGoalStatusId treatmentGoalStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.treatmentPlanId = Objects.requireNonNull(treatmentPlanId, "treatmentPlanId must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.targetDate = Objects.requireNonNull(targetDate, "targetDate must not be null");
        this.completedAt = Objects.requireNonNull(completedAt, "completedAt must not be null");
        this.notes = Objects.requireNonNull(notes, "notes must not be null");
        this.treatmentGoalStatusId = Objects.requireNonNull(treatmentGoalStatusId, "treatmentGoalStatusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static TreatmentGoal register(
            TreatmentPlanId treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            TreatmentGoalStatusId treatmentGoalStatusId) {
        TreatmentGoalId id = TreatmentGoalId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoal aggregate = new TreatmentGoal(
                id,
                treatmentPlanId,
                description,
                targetDate,
                completedAt,
                notes,
                treatmentGoalStatusId,
                now,
                now);
        aggregate.recordEvent(new TreatmentGoalRegisteredEvent(id, now));
        return aggregate;
    }

    public static TreatmentGoal restore(
            TreatmentGoalId id,
            TreatmentPlanId treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            TreatmentGoalStatusId treatmentGoalStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentGoal(
                id,
                treatmentPlanId,
                description,
                targetDate,
                completedAt,
                notes,
                treatmentGoalStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            TreatmentPlanId treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            TreatmentGoalStatusId treatmentGoalStatusId) {
        this.treatmentPlanId = Objects.requireNonNull(treatmentPlanId, "treatmentPlanId must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.targetDate = Objects.requireNonNull(targetDate, "targetDate must not be null");
        this.completedAt = Objects.requireNonNull(completedAt, "completedAt must not be null");
        this.notes = Objects.requireNonNull(notes, "notes must not be null");
        this.treatmentGoalStatusId = Objects.requireNonNull(treatmentGoalStatusId, "treatmentGoalStatusId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new TreatmentGoalUpdatedEvent(
                        this.id,
                        this.treatmentPlanId,
                        this.description,
                        this.targetDate,
                        this.completedAt,
                        this.notes,
                        this.treatmentGoalStatusId,
                        this.updatedAt));
    }

    public TreatmentGoalId id() {
        return id;
    }

    public TreatmentPlanId treatmentPlanId() {
        return treatmentPlanId;
    }

    public String description() {
        return description;
    }

    public LocalDate targetDate() {
        return targetDate;
    }

    public LocalDateTime completedAt() {
        return completedAt;
    }

    public String notes() {
        return notes;
    }

    public TreatmentGoalStatusId treatmentGoalStatusId() {
        return treatmentGoalStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
