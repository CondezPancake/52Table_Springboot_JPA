package springboot.domain.common.exception;

import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentStatusNotFoundException extends RuntimeException {
    public TreatmentStatusNotFoundException(TreatmentStatusId id) {
        super("TreatmentStatus not found with id: " + id.value());
    }
}
