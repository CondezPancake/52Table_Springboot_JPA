package springboot.domain.chatescalationstatushistory.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatEscalationStatusHistoryId(UUID value) {
    public ChatEscalationStatusHistoryId {
        Objects.requireNonNull(value, "ChatEscalationStatusHistoryId value must not be null");
    }

    public static ChatEscalationStatusHistoryId generate() {
        return new ChatEscalationStatusHistoryId(UUID.randomUUID());
    }
}
