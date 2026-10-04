package springboot.application.chatairun.command;

import java.util.Objects;


import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

public record UpdateChatAiRunCommand(
        ChatAiRunId id,
        ChatConversationId conversationId,
        ChatMessageId messageId,
        AiModelId modelId,
        AiRunStatusId aiRunStatusId
) {
    public UpdateChatAiRunCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageId, "messageId must not be null");
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(aiRunStatusId, "aiRunStatusId must not be null");
    }
}
