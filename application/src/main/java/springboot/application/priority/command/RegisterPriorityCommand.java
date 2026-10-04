package springboot.application.priority.command;

import java.util.Objects;




public record RegisterPriorityCommand(
        String namePriority
) {
    public RegisterPriorityCommand {
        Objects.requireNonNull(namePriority, "namePriority must not be null");
    }
}
