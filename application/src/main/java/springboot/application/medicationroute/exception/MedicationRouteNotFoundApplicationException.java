package springboot.application.medicationroute.exception;

import springboot.application.common.exception.ApplicationException;

public class MedicationRouteNotFoundApplicationException extends ApplicationException {
    public MedicationRouteNotFoundApplicationException(String message) {
        super(message);
    }
}
