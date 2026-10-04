package springboot.application.treatmentstatus.exception;

import springboot.application.common.exception.ApplicationException;

public class TreatmentStatusNotFoundApplicationException extends ApplicationException {
    public TreatmentStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
