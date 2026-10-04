package springboot.domain.aimodel.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AiModelId(UUID value) {
    public AiModelId {
        Objects.requireNonNull(value, "AiModelId value must not be null");
    }

    public static AiModelId generate() {
        return new AiModelId(UUID.randomUUID());
    }
}
