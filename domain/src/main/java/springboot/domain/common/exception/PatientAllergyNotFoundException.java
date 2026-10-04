package springboot.domain.common.exception;

import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;

public class PatientAllergyNotFoundException extends RuntimeException {
    public PatientAllergyNotFoundException(PatientAllergyId id) {
        super("PatientAllergy not found with id: " + id.value());
    }
}
