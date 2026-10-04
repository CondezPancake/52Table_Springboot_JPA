package springboot.domain.chatescalationassignment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatescalationassignment.event.ChatEscalationAssignmentRegisteredEvent;
import springboot.domain.chatescalationassignment.event.ChatEscalationAssignmentUpdatedEvent;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class ChatEscalationAssignment extends AggregateRoot {
    private final ChatEscalationAssignmentId id;
    private ChatEscalationId escalationId;
    private ProfessionalId professionalId;
    private LocalDateTime assignedAt;


    private ChatEscalationAssignment(
            ChatEscalationAssignmentId id,
            ChatEscalationId escalationId,
            ProfessionalId professionalId,
            LocalDateTime assignedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.escalationId = Objects.requireNonNull(escalationId, "escalationId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.assignedAt = Objects.requireNonNull(assignedAt, "assignedAt must not be null");

    }

    public static ChatEscalationAssignment register(
            ChatEscalationId escalationId,
            ProfessionalId professionalId,
            LocalDateTime assignedAt) {
        ChatEscalationAssignmentId id = ChatEscalationAssignmentId.generate();
        LocalDateTime occurredOn = LocalDateTime.now();
        ChatEscalationAssignment aggregate = new ChatEscalationAssignment(
                id,
                escalationId,
                professionalId,
                assignedAt);
        aggregate.recordEvent(new ChatEscalationAssignmentRegisteredEvent(id, occurredOn));
        return aggregate;
    }

    public static ChatEscalationAssignment restore(
            ChatEscalationAssignmentId id,
            ChatEscalationId escalationId,
            ProfessionalId professionalId,
            LocalDateTime assignedAt) {
        return new ChatEscalationAssignment(
                id,
                escalationId,
                professionalId,
                assignedAt);
    }

    public void update(
            ChatEscalationId escalationId,
            ProfessionalId professionalId,
            LocalDateTime assignedAt) {
        this.escalationId = Objects.requireNonNull(escalationId, "escalationId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.assignedAt = Objects.requireNonNull(assignedAt, "assignedAt must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new ChatEscalationAssignmentUpdatedEvent(
                        this.id,
                        this.escalationId,
                        this.professionalId,
                        this.assignedAt,
                        occurredOn));
    }

    public ChatEscalationAssignmentId id() {
        return id;
    }

    public ChatEscalationId escalationId() {
        return escalationId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public LocalDateTime assignedAt() {
        return assignedAt;
    }


}
