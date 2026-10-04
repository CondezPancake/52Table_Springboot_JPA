package springboot.application.messagetype.command;

import java.util.Objects;


import springboot.domain.messagetype.model.valueobject.MessageTypeId;


public record UpdateMessageTypeCommand(
        MessageTypeId id,
        String nameType
) {
    public UpdateMessageTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameType, "nameType must not be null");
    }
}
