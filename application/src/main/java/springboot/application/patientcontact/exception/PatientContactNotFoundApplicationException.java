package springboot.application.patientcontact.exception;

import springboot.application.common.exception.ApplicationException;

public class PatientContactNotFoundApplicationException extends ApplicationException {
    public PatientContactNotFoundApplicationException(String message) {
        super(message);
    }
}
