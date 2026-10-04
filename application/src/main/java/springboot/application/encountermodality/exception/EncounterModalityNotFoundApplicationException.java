package springboot.application.encountermodality.exception;

import springboot.application.common.exception.ApplicationException;

public class EncounterModalityNotFoundApplicationException extends ApplicationException {
    public EncounterModalityNotFoundApplicationException(String message) {
        super(message);
    }
}
