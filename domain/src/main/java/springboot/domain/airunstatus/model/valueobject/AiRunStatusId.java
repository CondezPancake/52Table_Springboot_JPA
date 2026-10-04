package springboot.domain.airunstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AiRunStatusId(UUID value) {
    public AiRunStatusId {
        Objects.requireNonNull(value, "AiRunStatusId value must not be null");
    }

    public static AiRunStatusId generate() {
        return new AiRunStatusId(UUID.randomUUID());
    }
}
