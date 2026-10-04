package springboot.domain.common.exception;

import springboot.domain.country.model.valueobject.CountryId;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(CountryId id) {
        super("Country not found with id: " + id.value());
    }
}
