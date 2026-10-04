package springboot.domain.common.exception;

import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public class ProfessionalStudyNotFoundException extends RuntimeException {
    public ProfessionalStudyNotFoundException(ProfessionalStudyId id) {
        super("ProfessionalStudy not found with id: " + id.value());
    }
}
