package springboot.infrastructure.chatmessage.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateChatMessageRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "messageTypeId is required")
        UUID messageTypeId,

        @NotNull(message = "participantId is required")
        UUID participantId,

        @NotNull(message = "content is required")
        String content,

        @NotNull(message = "metadata is required")
        String metadata
) {
}
