package springboot.domain.common.exception;

import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public class TreatmentPlanNotFoundException extends RuntimeException {
    public TreatmentPlanNotFoundException(TreatmentPlanId id) {
        super("TreatmentPlan not found with id: " + id.value());
    }
}
