package springboot.domain.chatmessage.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatMessageId(UUID value) {
    public ChatMessageId {
        Objects.requireNonNull(value, "ChatMessageId value must not be null");
    }

    public static ChatMessageId generate() {
        return new ChatMessageId(UUID.randomUUID());
    }
}
