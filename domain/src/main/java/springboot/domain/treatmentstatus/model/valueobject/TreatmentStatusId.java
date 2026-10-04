package springboot.domain.treatmentstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record TreatmentStatusId(UUID value) {
    public TreatmentStatusId {
        Objects.requireNonNull(value, "TreatmentStatusId value must not be null");
    }

    public static TreatmentStatusId generate() {
        return new TreatmentStatusId(UUID.randomUUID());
    }
}
