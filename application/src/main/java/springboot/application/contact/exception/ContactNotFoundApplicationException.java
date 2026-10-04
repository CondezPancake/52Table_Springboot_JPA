package springboot.application.contact.exception;

import springboot.application.common.exception.ApplicationException;

public class ContactNotFoundApplicationException extends ApplicationException {
    public ContactNotFoundApplicationException(String message) {
        super(message);
    }
}
