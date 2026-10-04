package springboot.domain.chatairunmetric.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatAiRunMetricId(UUID value) {
    public ChatAiRunMetricId {
        Objects.requireNonNull(value, "ChatAiRunMetricId value must not be null");
    }

    public static ChatAiRunMetricId generate() {
        return new ChatAiRunMetricId(UUID.randomUUID());
    }
}
