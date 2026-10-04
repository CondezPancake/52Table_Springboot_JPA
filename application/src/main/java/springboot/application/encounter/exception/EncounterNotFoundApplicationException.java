package springboot.application.encounter.exception;

import springboot.application.common.exception.ApplicationException;

public class EncounterNotFoundApplicationException extends ApplicationException {
    public EncounterNotFoundApplicationException(String message) {
        super(message);
    }
}
