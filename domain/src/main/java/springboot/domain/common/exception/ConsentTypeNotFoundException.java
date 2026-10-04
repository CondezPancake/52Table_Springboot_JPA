package springboot.domain.common.exception;

import springboot.domain.consenttype.model.valueobject.ConsentTypeId;

public class ConsentTypeNotFoundException extends RuntimeException {
    public ConsentTypeNotFoundException(ConsentTypeId id) {
        super("ConsentType not found with id: " + id.value());
    }
}
