package springboot.domain.chatconversation.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatConversationId(UUID value) {
    public ChatConversationId {
        Objects.requireNonNull(value, "ChatConversationId value must not be null");
    }

    public static ChatConversationId generate() {
        return new ChatConversationId(UUID.randomUUID());
    }
}
