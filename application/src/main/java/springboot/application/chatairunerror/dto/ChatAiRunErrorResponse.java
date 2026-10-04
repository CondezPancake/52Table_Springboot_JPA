package springboot.application.chatairunerror.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ChatAiRunErrorResponse(
        UUID id,
        UUID aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId,
        LocalDateTime createdAt
) {
}
