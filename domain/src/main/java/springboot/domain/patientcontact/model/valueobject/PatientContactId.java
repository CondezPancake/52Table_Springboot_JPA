package springboot.domain.patientcontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record PatientContactId(UUID value) {
    public PatientContactId {
        Objects.requireNonNull(value, "PatientContactId value must not be null");
    }

    public static PatientContactId generate() {
        return new PatientContactId(UUID.randomUUID());
    }
}
