package springboot.domain.chatconversationaisetting.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

public record ChatConversationAiSettingUpdatedEvent(
        ChatConversationAiSettingId id,
        ChatConversationId conversationId,
        boolean aiEnabled,
        AiModelId defaultModelId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatConversationAiSettingUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(defaultModelId, "defaultModelId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
