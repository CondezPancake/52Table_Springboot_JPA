package springboot.domain.common.exception;

import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public class TreatmentGoalNotFoundException extends RuntimeException {
    public TreatmentGoalNotFoundException(TreatmentGoalId id) {
        super("TreatmentGoal not found with id: " + id.value());
    }
}
