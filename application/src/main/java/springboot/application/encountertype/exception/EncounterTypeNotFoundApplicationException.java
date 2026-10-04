package springboot.application.encountertype.exception;

import springboot.application.common.exception.ApplicationException;

public class EncounterTypeNotFoundApplicationException extends ApplicationException {
    public EncounterTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
