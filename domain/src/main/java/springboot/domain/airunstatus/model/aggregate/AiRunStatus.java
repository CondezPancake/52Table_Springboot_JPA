package springboot.domain.airunstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.airunstatus.event.AiRunStatusRegisteredEvent;
import springboot.domain.airunstatus.event.AiRunStatusUpdatedEvent;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;


public class AiRunStatus extends AggregateRoot {
    private final AiRunStatusId id;
    private String nameStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AiRunStatus(
            AiRunStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameStatus = Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static AiRunStatus register(
            String nameStatus) {
        AiRunStatusId id = AiRunStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        AiRunStatus aggregate = new AiRunStatus(
                id,
                nameStatus,
                now,
                now);
        aggregate.recordEvent(new AiRunStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static AiRunStatus restore(
            AiRunStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AiRunStatus(
                id,
                nameStatus,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameStatus) {
        this.nameStatus = Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new AiRunStatusUpdatedEvent(
                        this.id,
                        this.nameStatus,
                        this.updatedAt));
    }

    public AiRunStatusId id() {
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
