package springboot.application.escalationstatus.command;

import java.util.Objects;




public record RegisterEscalationStatusCommand(
        String nameStatus
) {
    public RegisterEscalationStatusCommand {
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
    }
}
