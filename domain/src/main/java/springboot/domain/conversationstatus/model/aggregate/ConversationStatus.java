package springboot.domain.conversationstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.conversationstatus.event.ConversationStatusRegisteredEvent;
import springboot.domain.conversationstatus.event.ConversationStatusUpdatedEvent;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;


public class ConversationStatus extends AggregateRoot {
    private final ConversationStatusId id;
    private String nameStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ConversationStatus(
            ConversationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameStatus = Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ConversationStatus register(
            String nameStatus) {
        ConversationStatusId id = ConversationStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        ConversationStatus aggregate = new ConversationStatus(
                id,
                nameStatus,
                now,
                now);
        aggregate.recordEvent(new ConversationStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static ConversationStatus restore(
            ConversationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ConversationStatus(
                id,
                nameStatus,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameStatus) {
        this.nameStatus = Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ConversationStatusUpdatedEvent(
                        this.id,
                        this.nameStatus,
                        this.updatedAt));
    }

    public ConversationStatusId id() {
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
