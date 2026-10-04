package springboot.domain.treatmentstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.treatmentstatus.event.TreatmentStatusRegisteredEvent;
import springboot.domain.treatmentstatus.event.TreatmentStatusUpdatedEvent;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;


public class TreatmentStatus extends AggregateRoot {
    private final TreatmentStatusId id;
    private String code;
    private String name;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentStatus(
            TreatmentStatusId id,
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

    public static TreatmentStatus register(
            String code,
            String name,
            boolean active) {
        TreatmentStatusId id = TreatmentStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentStatus aggregate = new TreatmentStatus(
                id,
                code,
                name,
                active,
                now,
                now);
        aggregate.recordEvent(new TreatmentStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static TreatmentStatus restore(
            TreatmentStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentStatus(
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
        recordEvent(new TreatmentStatusUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.updatedAt));
    }

    public TreatmentStatusId id() {
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
