package springboot.application.chatairunerror.command;

import java.util.Objects;


import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public record UpdateChatAiRunErrorCommand(
        ChatAiRunErrorId id,
        ChatAiRunId aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId
) {
    public UpdateChatAiRunErrorCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        Objects.requireNonNull(errorMessage, "errorMessage must not be null");
        Objects.requireNonNull(errorCode, "errorCode must not be null");
        Objects.requireNonNull(providerErrorId, "providerErrorId must not be null");
    }
}
