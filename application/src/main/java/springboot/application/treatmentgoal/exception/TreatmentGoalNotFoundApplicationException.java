package springboot.application.treatmentgoal.exception;

import springboot.application.common.exception.ApplicationException;

public class TreatmentGoalNotFoundApplicationException extends ApplicationException {
    public TreatmentGoalNotFoundApplicationException(String message) {
        super(message);
    }
}
