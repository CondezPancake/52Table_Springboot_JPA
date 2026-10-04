package springboot.domain.chatconversationaisetting.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatconversationaisetting.event.ChatConversationAiSettingRegisteredEvent;
import springboot.domain.chatconversationaisetting.event.ChatConversationAiSettingUpdatedEvent;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

public class ChatConversationAiSetting extends AggregateRoot {
    private final ChatConversationAiSettingId id;
    private ChatConversationId conversationId;
    private boolean aiEnabled;
    private AiModelId defaultModelId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatConversationAiSetting(
            ChatConversationAiSettingId id,
            ChatConversationId conversationId,
            boolean aiEnabled,
            AiModelId defaultModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.aiEnabled = aiEnabled;
        this.defaultModelId = Objects.requireNonNull(defaultModelId, "defaultModelId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ChatConversationAiSetting register(
            ChatConversationId conversationId,
            boolean aiEnabled,
            AiModelId defaultModelId) {
        ChatConversationAiSettingId id = ChatConversationAiSettingId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatConversationAiSetting aggregate = new ChatConversationAiSetting(
                id,
                conversationId,
                aiEnabled,
                defaultModelId,
                now,
                now);
        aggregate.recordEvent(new ChatConversationAiSettingRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatConversationAiSetting restore(
            ChatConversationAiSettingId id,
            ChatConversationId conversationId,
            boolean aiEnabled,
            AiModelId defaultModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatConversationAiSetting(
                id,
                conversationId,
                aiEnabled,
                defaultModelId,
                createdAt,
                updatedAt);
    }

    public void update(
            ChatConversationId conversationId,
            boolean aiEnabled,
            AiModelId defaultModelId) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.aiEnabled = aiEnabled;
        this.defaultModelId = Objects.requireNonNull(defaultModelId, "defaultModelId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ChatConversationAiSettingUpdatedEvent(
                        this.id,
                        this.conversationId,
                        this.aiEnabled,
                        this.defaultModelId,
                        this.updatedAt));
    }

    public ChatConversationAiSettingId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public boolean aiEnabled() {
        return aiEnabled;
    }

    public AiModelId defaultModelId() {
        return defaultModelId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
