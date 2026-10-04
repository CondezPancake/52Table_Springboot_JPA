package springboot.domain.chatmessage.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatmessage.event.ChatMessageRegisteredEvent;
import springboot.domain.chatmessage.event.ChatMessageUpdatedEvent;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;

public class ChatMessage extends AggregateRoot {
    private final ChatMessageId id;
    private ChatConversationId conversationId;
    private MessageTypeId messageTypeId;
    private ChatParticipantId participantId;
    private String content;
    private String metadata;
    private final LocalDateTime createdAt;

    private ChatMessage(
            ChatMessageId id,
            ChatConversationId conversationId,
            MessageTypeId messageTypeId,
            ChatParticipantId participantId,
            String content,
            String metadata,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageTypeId = Objects.requireNonNull(messageTypeId, "messageTypeId must not be null");
        this.participantId = Objects.requireNonNull(participantId, "participantId must not be null");
        this.content = Objects.requireNonNull(content, "content must not be null");
        this.metadata = Objects.requireNonNull(metadata, "metadata must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static ChatMessage register(
            ChatConversationId conversationId,
            MessageTypeId messageTypeId,
            ChatParticipantId participantId,
            String content,
            String metadata) {
        ChatMessageId id = ChatMessageId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatMessage aggregate = new ChatMessage(
                id,
                conversationId,
                messageTypeId,
                participantId,
                content,
                metadata,
                now);
        aggregate.recordEvent(new ChatMessageRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatMessage restore(
            ChatMessageId id,
            ChatConversationId conversationId,
            MessageTypeId messageTypeId,
            ChatParticipantId participantId,
            String content,
            String metadata,
            LocalDateTime createdAt) {
        return new ChatMessage(
                id,
                conversationId,
                messageTypeId,
                participantId,
                content,
                metadata,
                createdAt);
    }

    public void update(
            ChatConversationId conversationId,
            MessageTypeId messageTypeId,
            ChatParticipantId participantId,
            String content,
            String metadata) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageTypeId = Objects.requireNonNull(messageTypeId, "messageTypeId must not be null");
        this.participantId = Objects.requireNonNull(participantId, "participantId must not be null");
        this.content = Objects.requireNonNull(content, "content must not be null");
        this.metadata = Objects.requireNonNull(metadata, "metadata must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new ChatMessageUpdatedEvent(
                        this.id,
                        this.conversationId,
                        this.messageTypeId,
                        this.participantId,
                        this.content,
                        this.metadata,
                        occurredOn));
    }

    public ChatMessageId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public MessageTypeId messageTypeId() {
        return messageTypeId;
    }

    public ChatParticipantId participantId() {
        return participantId;
    }

    public String content() {
        return content;
    }

    public String metadata() {
        return metadata;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
