package springboot.domain.clinicalnote.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ClinicalNoteId(UUID value) {
    public ClinicalNoteId {
        Objects.requireNonNull(value, "ClinicalNoteId value must not be null");
    }

    public static ClinicalNoteId generate() {
        return new ClinicalNoteId(UUID.randomUUID());
    }
}
