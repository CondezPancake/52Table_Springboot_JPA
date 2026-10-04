package springboot.domain.common.exception;

import springboot.domain.patientcontact.model.valueobject.PatientContactId;

public class PatientContactNotFoundException extends RuntimeException {
    public PatientContactNotFoundException(PatientContactId id) {
        super("PatientContact not found with id: " + id.value());
    }
}
