package springboot.domain.escalationstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record EscalationStatusId(UUID value) {
    public EscalationStatusId {
        Objects.requireNonNull(value, "EscalationStatusId value must not be null");
    }

    public static EscalationStatusId generate() {
        return new EscalationStatusId(UUID.randomUUID());
    }
}
