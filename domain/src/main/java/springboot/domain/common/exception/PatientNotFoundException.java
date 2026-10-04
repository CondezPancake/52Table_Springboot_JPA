package springboot.domain.common.exception;

import springboot.domain.patient.model.valueobject.PatientId;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(PatientId id) {
        super("Patient not found with id: " + id.value());
    }
}
