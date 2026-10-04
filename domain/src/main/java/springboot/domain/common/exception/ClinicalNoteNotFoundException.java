package springboot.domain.common.exception;

import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public class ClinicalNoteNotFoundException extends RuntimeException {
    public ClinicalNoteNotFoundException(ClinicalNoteId id) {
        super("ClinicalNote not found with id: " + id.value());
    }
}
