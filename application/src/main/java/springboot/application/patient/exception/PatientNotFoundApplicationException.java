package springboot.application.patient.exception;

import springboot.application.common.exception.ApplicationException;

public class PatientNotFoundApplicationException extends ApplicationException {
    public PatientNotFoundApplicationException(String message) {
        super(message);
    }
}
