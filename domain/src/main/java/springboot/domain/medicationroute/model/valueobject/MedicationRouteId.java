package springboot.domain.medicationroute.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record MedicationRouteId(UUID value) {
    public MedicationRouteId {
        Objects.requireNonNull(value, "MedicationRouteId value must not be null");
    }

    public static MedicationRouteId generate() {
        return new MedicationRouteId(UUID.randomUUID());
    }
}
