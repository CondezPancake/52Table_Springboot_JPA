package springboot.application.risklevel.exception;

import springboot.application.common.exception.ApplicationException;

public class RiskLevelNotFoundApplicationException extends ApplicationException {
    public RiskLevelNotFoundApplicationException(String message) {
        super(message);
    }
}
