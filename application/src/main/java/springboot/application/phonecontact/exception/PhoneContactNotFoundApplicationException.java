package springboot.application.phonecontact.exception;

import springboot.application.common.exception.ApplicationException;

public class PhoneContactNotFoundApplicationException extends ApplicationException {
    public PhoneContactNotFoundApplicationException(String message) {
        super(message);
    }
}
