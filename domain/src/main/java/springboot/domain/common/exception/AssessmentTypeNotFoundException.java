package springboot.domain.common.exception;

import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public class AssessmentTypeNotFoundException extends RuntimeException {
    public AssessmentTypeNotFoundException(AssessmentTypeId id) {
        super("AssessmentType not found with id: " + id.value());
    }
}
