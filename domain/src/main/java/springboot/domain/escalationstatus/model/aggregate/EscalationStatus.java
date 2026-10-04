package springboot.domain.escalationstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.escalationstatus.event.EscalationStatusRegisteredEvent;
import springboot.domain.escalationstatus.event.EscalationStatusUpdatedEvent;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;


public class EscalationStatus extends AggregateRoot {
    private final EscalationStatusId id;
    private String nameStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EscalationStatus(
            EscalationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameStatus = Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static EscalationStatus register(
            String nameStatus) {
        EscalationStatusId id = EscalationStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        EscalationStatus aggregate = new EscalationStatus(
                id,
                nameStatus,
                now,
                now);
        aggregate.recordEvent(new EscalationStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static EscalationStatus restore(
            EscalationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EscalationStatus(
                id,
                nameStatus,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameStatus) {
        this.nameStatus = Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new EscalationStatusUpdatedEvent(
                        this.id,
                        this.nameStatus,
                        this.updatedAt));
    }

    public EscalationStatusId id() {
        return id;
    }

    public String nameStatus() {
        return nameStatus;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
