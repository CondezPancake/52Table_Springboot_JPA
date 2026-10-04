package springboot.application.clinicalrecordstatus.command;

import java.util.Objects;




public record RegisterClinicalRecordStatusCommand(
        String code,
        String name
) {
    public RegisterClinicalRecordStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
