package springboot.domain.chatairun.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

public record ChatAiRunUpdatedEvent(
        ChatAiRunId id,
        ChatConversationId conversationId,
        ChatMessageId messageId,
        AiModelId modelId,
        AiRunStatusId aiRunStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatAiRunUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageId, "messageId must not be null");
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(aiRunStatusId, "aiRunStatusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
