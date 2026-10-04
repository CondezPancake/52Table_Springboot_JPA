package springboot.application.treatmentstatus.command;

import java.util.Objects;


import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;


public record UpdateTreatmentStatusCommand(
        TreatmentStatusId id,
        String code,
        String name,
        boolean active
) {
    public UpdateTreatmentStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
