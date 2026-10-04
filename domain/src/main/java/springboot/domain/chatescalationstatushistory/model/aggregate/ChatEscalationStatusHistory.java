package springboot.domain.chatescalationstatushistory.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryRegisteredEvent;
import springboot.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryUpdatedEvent;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class ChatEscalationStatusHistory extends AggregateRoot {
    private final ChatEscalationStatusHistoryId id;
    private ChatEscalationId escalationId;
    private EscalationStatusId escalationStatusId;
    private LocalDateTime changedAt;
    private final LocalDateTime createdAt;

    private ChatEscalationStatusHistory(
            ChatEscalationStatusHistoryId id,
            ChatEscalationId escalationId,
            EscalationStatusId escalationStatusId,
            LocalDateTime changedAt,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.escalationId = Objects.requireNonNull(escalationId, "escalationId must not be null");
        this.escalationStatusId = Objects.requireNonNull(escalationStatusId, "escalationStatusId must not be null");
        this.changedAt = Objects.requireNonNull(changedAt, "changedAt must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static ChatEscalationStatusHistory register(
            ChatEscalationId escalationId,
            EscalationStatusId escalationStatusId,
            LocalDateTime changedAt) {
        ChatEscalationStatusHistoryId id = ChatEscalationStatusHistoryId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatEscalationStatusHistory aggregate = new ChatEscalationStatusHistory(
                id,
                escalationId,
                escalationStatusId,
                changedAt,
                now);
        aggregate.recordEvent(new ChatEscalationStatusHistoryRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatEscalationStatusHistory restore(
            ChatEscalationStatusHistoryId id,
            ChatEscalationId escalationId,
            EscalationStatusId escalationStatusId,
            LocalDateTime changedAt,
            LocalDateTime createdAt) {
        return new ChatEscalationStatusHistory(
                id,
                escalationId,
                escalationStatusId,
                changedAt,
                createdAt);
    }

    public void update(
            ChatEscalationId escalationId,
            EscalationStatusId escalationStatusId,
            LocalDateTime changedAt) {
        this.escalationId = Objects.requireNonNull(escalationId, "escalationId must not be null");
        this.escalationStatusId = Objects.requireNonNull(escalationStatusId, "escalationStatusId must not be null");
        this.changedAt = Objects.requireNonNull(changedAt, "changedAt must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new ChatEscalationStatusHistoryUpdatedEvent(
                        this.id,
                        this.escalationId,
                        this.escalationStatusId,
                        this.changedAt,
                        occurredOn));
    }

    public ChatEscalationStatusHistoryId id() {
        return id;
    }

    public ChatEscalationId escalationId() {
        return escalationId;
    }

    public EscalationStatusId escalationStatusId() {
        return escalationStatusId;
    }

    public LocalDateTime changedAt() {
        return changedAt;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
