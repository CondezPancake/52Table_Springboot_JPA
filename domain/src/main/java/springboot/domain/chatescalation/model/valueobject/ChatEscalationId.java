package springboot.domain.chatescalation.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatEscalationId(UUID value) {
    public ChatEscalationId {
        Objects.requireNonNull(value, "ChatEscalationId value must not be null");
    }

    public static ChatEscalationId generate() {
        return new ChatEscalationId(UUID.randomUUID());
    }
}
