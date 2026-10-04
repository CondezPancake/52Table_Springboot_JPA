package springboot.application.citymunicipality.exception;

import springboot.application.common.exception.ApplicationException;

public class CityMunicipalityNotFoundApplicationException extends ApplicationException {
    public CityMunicipalityNotFoundApplicationException(String message) {
        super(message);
    }
}
