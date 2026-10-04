package springboot.domain.chatairun.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatairun.event.ChatAiRunRegisteredEvent;
import springboot.domain.chatairun.event.ChatAiRunUpdatedEvent;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

public class ChatAiRun extends AggregateRoot {
    private final ChatAiRunId id;
    private ChatConversationId conversationId;
    private ChatMessageId messageId;
    private AiModelId modelId;
    private AiRunStatusId aiRunStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatAiRun(
            ChatAiRunId id,
            ChatConversationId conversationId,
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageId = Objects.requireNonNull(messageId, "messageId must not be null");
        this.modelId = Objects.requireNonNull(modelId, "modelId must not be null");
        this.aiRunStatusId = Objects.requireNonNull(aiRunStatusId, "aiRunStatusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ChatAiRun register(
            ChatConversationId conversationId,
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId) {
        ChatAiRunId id = ChatAiRunId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatAiRun aggregate = new ChatAiRun(
                id,
                conversationId,
                messageId,
                modelId,
                aiRunStatusId,
                now,
                now);
        aggregate.recordEvent(new ChatAiRunRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatAiRun restore(
            ChatAiRunId id,
            ChatConversationId conversationId,
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatAiRun(
                id,
                conversationId,
                messageId,
                modelId,
                aiRunStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            ChatConversationId conversationId,
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageId = Objects.requireNonNull(messageId, "messageId must not be null");
        this.modelId = Objects.requireNonNull(modelId, "modelId must not be null");
        this.aiRunStatusId = Objects.requireNonNull(aiRunStatusId, "aiRunStatusId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ChatAiRunUpdatedEvent(
                        this.id,
                        this.conversationId,
                        this.messageId,
                        this.modelId,
                        this.aiRunStatusId,
                        this.updatedAt));
    }

    public ChatAiRunId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public ChatMessageId messageId() {
        return messageId;
    }

    public AiModelId modelId() {
        return modelId;
    }

    public AiRunStatusId aiRunStatusId() {
        return aiRunStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
