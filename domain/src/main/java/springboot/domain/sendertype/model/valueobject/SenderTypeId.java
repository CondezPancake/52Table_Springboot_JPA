package springboot.domain.sendertype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SenderTypeId(UUID value) {
    public SenderTypeId {
        Objects.requireNonNull(value, "SenderTypeId value must not be null");
    }

    public static SenderTypeId generate() {
        return new SenderTypeId(UUID.randomUUID());
    }
}
