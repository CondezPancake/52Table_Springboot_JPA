package springboot.infrastructure.chatairun.adapters.in.rest.dtos;

import java.util.UUID;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChatAiRunRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "messageId is required")
        UUID messageId,

        @NotNull(message = "modelId is required")
        UUID modelId,

        @NotNull(message = "aiRunStatusId is required")
        UUID aiRunStatusId
) {
}
