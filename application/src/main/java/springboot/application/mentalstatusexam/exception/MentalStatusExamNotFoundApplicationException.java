package springboot.application.mentalstatusexam.exception;

import springboot.application.common.exception.ApplicationException;

public class MentalStatusExamNotFoundApplicationException extends ApplicationException {
    public MentalStatusExamNotFoundApplicationException(String message) {
        super(message);
    }
}
