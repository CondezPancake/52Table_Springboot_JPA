package springboot.domain.gender.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record GenderId(UUID value) {
    public GenderId {
        Objects.requireNonNull(value, "GenderId value must not be null");
    }

    public static GenderId generate() {
        return new GenderId(UUID.randomUUID());
    }
}
