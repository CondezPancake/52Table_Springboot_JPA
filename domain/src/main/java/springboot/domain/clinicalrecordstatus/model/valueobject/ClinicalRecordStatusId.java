package springboot.domain.clinicalrecordstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ClinicalRecordStatusId(UUID value) {
    public ClinicalRecordStatusId {
        Objects.requireNonNull(value, "ClinicalRecordStatusId value must not be null");
    }

    public static ClinicalRecordStatusId generate() {
        return new ClinicalRecordStatusId(UUID.randomUUID());
    }
}
