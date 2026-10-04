package springboot.application.emailcontact.exception;

import springboot.application.common.exception.ApplicationException;

public class EmailContactNotFoundApplicationException extends ApplicationException {
    public EmailContactNotFoundApplicationException(String message) {
        super(message);
    }
}
