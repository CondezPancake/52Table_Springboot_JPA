package springboot.domain.encounter.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record EncounterId(UUID value) {
    public EncounterId {
        Objects.requireNonNull(value, "EncounterId value must not be null");
    }

    public static EncounterId generate() {
        return new EncounterId(UUID.randomUUID());
    }
}
