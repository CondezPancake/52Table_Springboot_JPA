package springboot.domain.chatescalationassignment.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatEscalationAssignmentId(UUID value) {
    public ChatEscalationAssignmentId {
        Objects.requireNonNull(value, "ChatEscalationAssignmentId value must not be null");
    }

    public static ChatEscalationAssignmentId generate() {
        return new ChatEscalationAssignmentId(UUID.randomUUID());
    }
}
