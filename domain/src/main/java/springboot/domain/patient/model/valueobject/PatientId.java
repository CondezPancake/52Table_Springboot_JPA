package springboot.domain.patient.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record PatientId(UUID value) {
    public PatientId {
        Objects.requireNonNull(value, "PatientId value must not be null");
    }

    public static PatientId generate() {
        return new PatientId(UUID.randomUUID());
    }
}
