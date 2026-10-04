package springboot.application.professionaltype.exception;

import springboot.application.common.exception.ApplicationException;

public class ProfessionalTypeNotFoundApplicationException extends ApplicationException {
    public ProfessionalTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
