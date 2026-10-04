package springboot.domain.encountertype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record EncounterTypeId(UUID value) {
    public EncounterTypeId {
        Objects.requireNonNull(value, "EncounterTypeId value must not be null");
    }

    public static EncounterTypeId generate() {
        return new EncounterTypeId(UUID.randomUUID());
    }
}
