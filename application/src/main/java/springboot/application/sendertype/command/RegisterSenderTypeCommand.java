package springboot.application.sendertype.command;

import java.util.Objects;




public record RegisterSenderTypeCommand(
        String nameType
) {
    public RegisterSenderTypeCommand {
        Objects.requireNonNull(nameType, "nameType must not be null");
    }
}
