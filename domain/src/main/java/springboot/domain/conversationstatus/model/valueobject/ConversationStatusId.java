package springboot.domain.conversationstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ConversationStatusId(UUID value) {
    public ConversationStatusId {
        Objects.requireNonNull(value, "ConversationStatusId value must not be null");
    }

    public static ConversationStatusId generate() {
        return new ConversationStatusId(UUID.randomUUID());
    }
}
