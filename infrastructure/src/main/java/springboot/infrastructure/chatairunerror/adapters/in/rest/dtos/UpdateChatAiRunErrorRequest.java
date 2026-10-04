package springboot.infrastructure.chatairunerror.adapters.in.rest.dtos;

import java.util.UUID;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateChatAiRunErrorRequest(
        @NotNull(message = "aiRunId is required")
        UUID aiRunId,

        @NotNull(message = "errorMessage is required")
        String errorMessage,

        @NotNull(message = "errorCode is required")
        @Size(max = 80, message = "errorCode must have at most 80 characters")
        String errorCode,

        @NotNull(message = "providerErrorId is required")
        @Size(max = 120, message = "providerErrorId must have at most 120 characters")
        String providerErrorId
) {
}
