package springboot.application.medicationroute.command;

import java.util.Objects;




public record RegisterMedicationRouteCommand(
        String code,
        String name,
        boolean active
) {
    public RegisterMedicationRouteCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
