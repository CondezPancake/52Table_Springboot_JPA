package springboot.application.chatconversationaisetting.command;

import java.util.Objects;


import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

public record UpdateChatConversationAiSettingCommand(
        ChatConversationAiSettingId id,
        ChatConversationId conversationId,
        boolean aiEnabled,
        AiModelId defaultModelId
) {
    public UpdateChatConversationAiSettingCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(defaultModelId, "defaultModelId must not be null");
    }
}
