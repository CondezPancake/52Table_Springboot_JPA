package springboot.domain.common.exception;

import springboot.domain.gender.model.valueobject.GenderId;

public class GenderNotFoundException extends RuntimeException {
    public GenderNotFoundException(GenderId id) {
        super("Gender not found with id: " + id.value());
    }
}
