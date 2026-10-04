package springboot.application.country.exception;

import springboot.application.common.exception.ApplicationException;

public class CountryNotFoundApplicationException extends ApplicationException {
    public CountryNotFoundApplicationException(String message) {
        super(message);
    }
}
