package springboot.application.study.exception;

import springboot.application.common.exception.ApplicationException;

public class StudyNotFoundApplicationException extends ApplicationException {
    public StudyNotFoundApplicationException(String message) {
        super(message);
    }
}
