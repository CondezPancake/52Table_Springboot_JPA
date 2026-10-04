package springboot.application.encounterstatus.exception;

import springboot.application.common.exception.ApplicationException;

public class EncounterStatusNotFoundApplicationException extends ApplicationException {
    public EncounterStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
