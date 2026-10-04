package springboot.application.treatmentgoalstatus.exception;

import springboot.application.common.exception.ApplicationException;

public class TreatmentGoalStatusNotFoundApplicationException extends ApplicationException {
    public TreatmentGoalStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
