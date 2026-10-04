package springboot.domain.treatmentgoalstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.treatmentgoalstatus.event.TreatmentGoalStatusRegisteredEvent;
import springboot.domain.treatmentgoalstatus.event.TreatmentGoalStatusUpdatedEvent;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;


public class TreatmentGoalStatus extends AggregateRoot {
    private final TreatmentGoalStatusId id;
    private String code;
    private String name;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentGoalStatus(
            TreatmentGoalStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static TreatmentGoalStatus register(
            String code,
            String name,
            boolean active) {
        TreatmentGoalStatusId id = TreatmentGoalStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoalStatus aggregate = new TreatmentGoalStatus(
                id,
                code,
                name,
                active,
                now,
                now);
        aggregate.recordEvent(new TreatmentGoalStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static TreatmentGoalStatus restore(
            TreatmentGoalStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentGoalStatus(
                id,
                code,
                name,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            boolean active) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new TreatmentGoalStatusUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.updatedAt));
    }

    public TreatmentGoalStatusId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
