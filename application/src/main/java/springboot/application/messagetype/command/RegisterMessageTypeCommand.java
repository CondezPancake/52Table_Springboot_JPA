package springboot.application.messagetype.command;

import java.util.Objects;




public record RegisterMessageTypeCommand(
        String nameType
) {
    public RegisterMessageTypeCommand {
        Objects.requireNonNull(nameType, "nameType must not be null");
    }
}
