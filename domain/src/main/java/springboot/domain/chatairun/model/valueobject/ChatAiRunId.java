package springboot.domain.chatairun.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatAiRunId(UUID value) {
    public ChatAiRunId {
        Objects.requireNonNull(value, "ChatAiRunId value must not be null");
    }

    public static ChatAiRunId generate() {
        return new ChatAiRunId(UUID.randomUUID());
    }
}
