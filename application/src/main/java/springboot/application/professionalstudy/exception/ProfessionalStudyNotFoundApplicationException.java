package springboot.application.professionalstudy.exception;

import springboot.application.common.exception.ApplicationException;

public class ProfessionalStudyNotFoundApplicationException extends ApplicationException {
    public ProfessionalStudyNotFoundApplicationException(String message) {
        super(message);
    }
}
