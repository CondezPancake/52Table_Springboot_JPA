package springboot.domain.common.exception;

import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public class TreatmentGoalStatusNotFoundException extends RuntimeException {
    public TreatmentGoalStatusNotFoundException(TreatmentGoalStatusId id) {
        super("TreatmentGoalStatus not found with id: " + id.value());
    }
}
