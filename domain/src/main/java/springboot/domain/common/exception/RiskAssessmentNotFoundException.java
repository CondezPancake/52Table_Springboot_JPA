package springboot.domain.common.exception;

import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;

public class RiskAssessmentNotFoundException extends RuntimeException {
    public RiskAssessmentNotFoundException(RiskAssessmentId id) {
        super("RiskAssessment not found with id: " + id.value());
    }
}
