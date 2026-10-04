package springboot.application.treatmentstatus.command;

import java.util.Objects;




public record RegisterTreatmentStatusCommand(
        String code,
        String name,
        boolean active
) {
    public RegisterTreatmentStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
