package springboot.domain.common.exception;

import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;

public class MedicationRouteNotFoundException extends RuntimeException {
    public MedicationRouteNotFoundException(MedicationRouteId id) {
        super("MedicationRoute not found with id: " + id.value());
    }
}
