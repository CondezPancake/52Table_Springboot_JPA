package springboot.domain.common.exception;

import springboot.domain.professional.model.valueobject.ProfessionalId;

public class ProfessionalNotFoundException extends RuntimeException {
    public ProfessionalNotFoundException(ProfessionalId id) {
        super("Professional not found with id: " + id.value());
    }
}
