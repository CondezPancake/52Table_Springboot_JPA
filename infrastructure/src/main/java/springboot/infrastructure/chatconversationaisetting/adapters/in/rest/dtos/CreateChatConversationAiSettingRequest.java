package springboot.infrastructure.chatconversationaisetting.adapters.in.rest.dtos;

import java.util.UUID;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChatConversationAiSettingRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "aiEnabled is required")
        Boolean aiEnabled,

        @NotNull(message = "defaultModelId is required")
        UUID defaultModelId
) {
}
