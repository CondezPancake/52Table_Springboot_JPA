package springboot.application.professional.exception;

import springboot.application.common.exception.ApplicationException;

public class ProfessionalNotFoundApplicationException extends ApplicationException {
    public ProfessionalNotFoundApplicationException(String message) {
        super(message);
    }
}
