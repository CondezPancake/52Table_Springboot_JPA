package springboot.domain.messagetype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record MessageTypeId(UUID value) {
    public MessageTypeId {
        Objects.requireNonNull(value, "MessageTypeId value must not be null");
    }

    public static MessageTypeId generate() {
        return new MessageTypeId(UUID.randomUUID());
    }
}
