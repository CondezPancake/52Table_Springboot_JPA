package springboot.application.airunstatus.command;

import java.util.Objects;




public record RegisterAiRunStatusCommand(
        String nameStatus
) {
    public RegisterAiRunStatusCommand {
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
    }
}
