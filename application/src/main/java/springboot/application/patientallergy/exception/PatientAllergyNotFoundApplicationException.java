package springboot.application.patientallergy.exception;

import springboot.application.common.exception.ApplicationException;

public class PatientAllergyNotFoundApplicationException extends ApplicationException {
    public PatientAllergyNotFoundApplicationException(String message) {
        super(message);
    }
}
