package springboot.infrastructure.chatescalation.adapters.in.rest.dtos;

import java.util.UUID;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChatEscalationRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "statusId is required")
        UUID statusId,

        @NotNull(message = "fromAi is required")
        Boolean fromAi,

        @NotNull(message = "reason is required")
        String reason
) {
}
