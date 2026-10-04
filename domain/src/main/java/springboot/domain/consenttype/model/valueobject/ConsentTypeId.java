package springboot.domain.consenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ConsentTypeId(UUID value) {
    public ConsentTypeId {
        Objects.requireNonNull(value, "ConsentTypeId value must not be null");
    }

    public static ConsentTypeId generate() {
        return new ConsentTypeId(UUID.randomUUID());
    }
}
