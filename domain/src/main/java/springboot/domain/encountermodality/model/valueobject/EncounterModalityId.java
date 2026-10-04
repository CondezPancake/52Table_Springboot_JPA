package springboot.domain.encountermodality.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record EncounterModalityId(UUID value) {
    public EncounterModalityId {
        Objects.requireNonNull(value, "EncounterModalityId value must not be null");
    }

    public static EncounterModalityId generate() {
        return new EncounterModalityId(UUID.randomUUID());
    }
}
