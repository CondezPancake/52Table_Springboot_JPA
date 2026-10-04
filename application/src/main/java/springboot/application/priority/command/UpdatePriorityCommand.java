package springboot.application.priority.command;

import java.util.Objects;


import springboot.domain.priority.model.valueobject.PriorityId;


public record UpdatePriorityCommand(
        PriorityId id,
        String namePriority
) {
    public UpdatePriorityCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(namePriority, "namePriority must not be null");
    }
}
