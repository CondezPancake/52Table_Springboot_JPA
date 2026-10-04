package springboot.domain.patientallergy.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record PatientAllergyId(UUID value) {
    public PatientAllergyId {
        Objects.requireNonNull(value, "PatientAllergyId value must not be null");
    }

    public static PatientAllergyId generate() {
        return new PatientAllergyId(UUID.randomUUID());
    }
}
