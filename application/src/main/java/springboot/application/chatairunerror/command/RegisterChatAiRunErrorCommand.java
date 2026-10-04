package springboot.application.chatairunerror.command;

import java.util.Objects;


import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public record RegisterChatAiRunErrorCommand(
        ChatAiRunId aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId
) {
    public RegisterChatAiRunErrorCommand {
        Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        Objects.requireNonNull(errorMessage, "errorMessage must not be null");
        Objects.requireNonNull(errorCode, "errorCode must not be null");
        Objects.requireNonNull(providerErrorId, "providerErrorId must not be null");
    }
}
