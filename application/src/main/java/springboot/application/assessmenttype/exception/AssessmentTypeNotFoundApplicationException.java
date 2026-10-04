package springboot.application.assessmenttype.exception;

import springboot.application.common.exception.ApplicationException;

public class AssessmentTypeNotFoundApplicationException extends ApplicationException {
    public AssessmentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
