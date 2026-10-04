package springboot.application.medicationroute.command;

import java.util.Objects;


import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;


public record UpdateMedicationRouteCommand(
        MedicationRouteId id,
        String code,
        String name,
        boolean active
) {
    public UpdateMedicationRouteCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
