package springboot.domain.chatconversation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatconversation.event.ChatConversationRegisteredEvent;
import springboot.domain.chatconversation.event.ChatConversationUpdatedEvent;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.priority.model.valueobject.PriorityId;

public class ChatConversation extends AggregateRoot {
    private final ChatConversationId id;
    private ConversationStatusId conversationStatusId;
    private PriorityId priorityId;
    private LocalDateTime lastMessageAt;
    private Boolean closed;
    private LocalDateTime closedAt;
    private UUID closedBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatConversation(
            ChatConversationId id,
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationStatusId = Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        this.priorityId = Objects.requireNonNull(priorityId, "priorityId must not be null");
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ChatConversation register(
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy) {
        ChatConversationId id = ChatConversationId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatConversation aggregate = new ChatConversation(
                id,
                conversationStatusId,
                priorityId,
                lastMessageAt,
                closed,
                closedAt,
                closedBy,
                now,
                now);
        aggregate.recordEvent(new ChatConversationRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatConversation restore(
            ChatConversationId id,
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatConversation(
                id,
                conversationStatusId,
                priorityId,
                lastMessageAt,
                closed,
                closedAt,
                closedBy,
                createdAt,
                updatedAt);
    }

    public void update(
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy) {
        this.conversationStatusId = Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        this.priorityId = Objects.requireNonNull(priorityId, "priorityId must not be null");
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ChatConversationUpdatedEvent(
                        this.id,
                        this.conversationStatusId,
                        this.priorityId,
                        this.lastMessageAt,
                        this.closed,
                        this.closedAt,
                        this.closedBy,
                        this.updatedAt));
    }

    public ChatConversationId id() {
        return id;
    }

    public ConversationStatusId conversationStatusId() {
        return conversationStatusId;
    }

    public PriorityId priorityId() {
        return priorityId;
    }

    public LocalDateTime lastMessageAt() {
        return lastMessageAt;
    }

    public Boolean closed() {
        return closed;
    }

    public LocalDateTime closedAt() {
        return closedAt;
    }

    public UUID closedBy() {
        return closedBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
