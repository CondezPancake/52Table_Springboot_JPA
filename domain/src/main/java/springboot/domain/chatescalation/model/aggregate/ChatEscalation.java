package springboot.domain.chatescalation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatescalation.event.ChatEscalationRegisteredEvent;
import springboot.domain.chatescalation.event.ChatEscalationUpdatedEvent;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class ChatEscalation extends AggregateRoot {
    private final ChatEscalationId id;
    private ChatConversationId conversationId;
    private EscalationStatusId statusId;
    private boolean fromAi;
    private String reason;
    private final LocalDateTime createdAt;

    private ChatEscalation(
            ChatEscalationId id,
            ChatConversationId conversationId,
            EscalationStatusId statusId,
            boolean fromAi,
            String reason,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.fromAi = fromAi;
        this.reason = Objects.requireNonNull(reason, "reason must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static ChatEscalation register(
            ChatConversationId conversationId,
            EscalationStatusId statusId,
            boolean fromAi,
            String reason) {
        ChatEscalationId id = ChatEscalationId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatEscalation aggregate = new ChatEscalation(
                id,
                conversationId,
                statusId,
                fromAi,
                reason,
                now);
        aggregate.recordEvent(new ChatEscalationRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatEscalation restore(
            ChatEscalationId id,
            ChatConversationId conversationId,
            EscalationStatusId statusId,
            boolean fromAi,
            String reason,
            LocalDateTime createdAt) {
        return new ChatEscalation(
                id,
                conversationId,
                statusId,
                fromAi,
                reason,
                createdAt);
    }

    public void update(
            ChatConversationId conversationId,
            EscalationStatusId statusId,
            boolean fromAi,
            String reason) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.fromAi = fromAi;
        this.reason = Objects.requireNonNull(reason, "reason must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new ChatEscalationUpdatedEvent(
                        this.id,
                        this.conversationId,
                        this.statusId,
                        this.fromAi,
                        this.reason,
                        occurredOn));
    }

    public ChatEscalationId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public EscalationStatusId statusId() {
        return statusId;
    }

    public boolean fromAi() {
        return fromAi;
    }

    public String reason() {
        return reason;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
