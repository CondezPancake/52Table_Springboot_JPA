package springboot.domain.messagetype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.messagetype.event.MessageTypeRegisteredEvent;
import springboot.domain.messagetype.event.MessageTypeUpdatedEvent;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;


public class MessageType extends AggregateRoot {
    private final MessageTypeId id;
    private String nameType;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private MessageType(
            MessageTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameType = Objects.requireNonNull(nameType, "nameType must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static MessageType register(
            String nameType) {
        MessageTypeId id = MessageTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        MessageType aggregate = new MessageType(
                id,
                nameType,
                now,
                now);
        aggregate.recordEvent(new MessageTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static MessageType restore(
            MessageTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new MessageType(
                id,
                nameType,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameType) {
        this.nameType = Objects.requireNonNull(nameType, "nameType must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new MessageTypeUpdatedEvent(
                        this.id,
                        this.nameType,
                        this.updatedAt));
    }

    public MessageTypeId id() {
        return id;
    }

    public String nameType() {
        return nameType;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
