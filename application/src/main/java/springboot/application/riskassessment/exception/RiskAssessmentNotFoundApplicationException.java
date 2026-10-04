package springboot.application.riskassessment.exception;

import springboot.application.common.exception.ApplicationException;

public class RiskAssessmentNotFoundApplicationException extends ApplicationException {
    public RiskAssessmentNotFoundApplicationException(String message) {
        super(message);
    }
}
