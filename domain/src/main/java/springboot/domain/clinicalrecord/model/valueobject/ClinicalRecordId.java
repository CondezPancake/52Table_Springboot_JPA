package springboot.domain.clinicalrecord.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ClinicalRecordId(UUID value) {
    public ClinicalRecordId {
        Objects.requireNonNull(value, "ClinicalRecordId value must not be null");
    }

    public static ClinicalRecordId generate() {
        return new ClinicalRecordId(UUID.randomUUID());
    }
}
