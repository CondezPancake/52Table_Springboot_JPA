package springboot.domain.common.exception;

import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public class ClinicalRecordStatusNotFoundException extends RuntimeException {
    public ClinicalRecordStatusNotFoundException(ClinicalRecordStatusId id) {
        super("ClinicalRecordStatus not found with id: " + id.value());
    }
}
