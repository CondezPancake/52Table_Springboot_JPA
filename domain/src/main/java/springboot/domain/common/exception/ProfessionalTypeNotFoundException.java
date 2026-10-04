package springboot.domain.common.exception;

import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class ProfessionalTypeNotFoundException extends RuntimeException {
    public ProfessionalTypeNotFoundException(ProfessionalTypeId id) {
        super("ProfessionalType not found with id: " + id.value());
    }
}
