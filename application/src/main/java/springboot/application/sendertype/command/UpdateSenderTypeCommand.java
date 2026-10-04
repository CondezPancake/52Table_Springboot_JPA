package springboot.application.sendertype.command;

import java.util.Objects;


import springboot.domain.sendertype.model.valueobject.SenderTypeId;


public record UpdateSenderTypeCommand(
        SenderTypeId id,
        String nameType
) {
    public UpdateSenderTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameType, "nameType must not be null");
    }
}
