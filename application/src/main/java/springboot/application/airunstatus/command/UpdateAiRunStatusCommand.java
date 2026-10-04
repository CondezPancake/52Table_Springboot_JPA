package springboot.application.airunstatus.command;

import java.util.Objects;


import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;


public record UpdateAiRunStatusCommand(
        AiRunStatusId id,
        String nameStatus
) {
    public UpdateAiRunStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
    }
}
