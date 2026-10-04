package springboot.application.clinicalrecordstatus.command;

import java.util.Objects;


import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;


public record UpdateClinicalRecordStatusCommand(
        ClinicalRecordStatusId id,
        String code,
        String name
) {
    public UpdateClinicalRecordStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
