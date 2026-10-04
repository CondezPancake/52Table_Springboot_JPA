package springboot.infrastructure.chatconversation.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChatConversationRequest(
        @NotNull(message = "conversationStatusId is required")
        UUID conversationStatusId,

        @NotNull(message = "priorityId is required")
        UUID priorityId,

        LocalDateTime lastMessageAt,

        Boolean closed,

        LocalDateTime closedAt,

        @Size(max = 36, message = "closedBy must have at most 36 characters")
        UUID closedBy
) {
}
