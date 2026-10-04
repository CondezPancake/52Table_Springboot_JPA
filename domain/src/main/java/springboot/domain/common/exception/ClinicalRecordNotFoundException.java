package springboot.domain.common.exception;

import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public class ClinicalRecordNotFoundException extends RuntimeException {
    public ClinicalRecordNotFoundException(ClinicalRecordId id) {
        super("ClinicalRecord not found with id: " + id.value());
    }
}
