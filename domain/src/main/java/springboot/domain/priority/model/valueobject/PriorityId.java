package springboot.domain.priority.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record PriorityId(UUID value) {
    public PriorityId {
        Objects.requireNonNull(value, "PriorityId value must not be null");
    }

    public static PriorityId generate() {
        return new PriorityId(UUID.randomUUID());
    }
}
