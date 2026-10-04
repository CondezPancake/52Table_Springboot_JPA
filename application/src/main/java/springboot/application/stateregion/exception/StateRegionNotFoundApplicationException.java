package springboot.application.stateregion.exception;

import springboot.application.common.exception.ApplicationException;

public class StateRegionNotFoundApplicationException extends ApplicationException {
    public StateRegionNotFoundApplicationException(String message) {
        super(message);
    }
}
