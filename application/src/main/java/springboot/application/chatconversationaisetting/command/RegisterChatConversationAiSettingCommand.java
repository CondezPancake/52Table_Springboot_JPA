package springboot.application.chatconversationaisetting.command;

import java.util.Objects;


import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

public record RegisterChatConversationAiSettingCommand(
        ChatConversationId conversationId,
        boolean aiEnabled,
        AiModelId defaultModelId
) {
    public RegisterChatConversationAiSettingCommand {
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(defaultModelId, "defaultModelId must not be null");
    }
}
