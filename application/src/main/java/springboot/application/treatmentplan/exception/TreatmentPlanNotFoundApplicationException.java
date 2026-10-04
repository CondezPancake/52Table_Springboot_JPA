package springboot.application.treatmentplan.exception;

import springboot.application.common.exception.ApplicationException;

public class TreatmentPlanNotFoundApplicationException extends ApplicationException {
    public TreatmentPlanNotFoundApplicationException(String message) {
        super(message);
    }
}
