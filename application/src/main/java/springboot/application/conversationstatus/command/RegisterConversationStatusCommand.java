package springboot.application.conversationstatus.command;

import java.util.Objects;




public record RegisterConversationStatusCommand(
        String nameStatus
) {
    public RegisterConversationStatusCommand {
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
    }
}
