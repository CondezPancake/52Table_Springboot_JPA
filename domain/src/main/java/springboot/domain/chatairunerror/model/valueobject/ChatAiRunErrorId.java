package springboot.domain.chatairunerror.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatAiRunErrorId(UUID value) {
    public ChatAiRunErrorId {
        Objects.requireNonNull(value, "ChatAiRunErrorId value must not be null");
    }

    public static ChatAiRunErrorId generate() {
        return new ChatAiRunErrorId(UUID.randomUUID());
    }
}
