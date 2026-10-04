package springboot.application.chatescalationassignment.usecase;

import springboot.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import springboot.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class GetChatEscalationAssignmentByIdUseCase {
    private final ChatEscalationAssignmentRepository repository;
    public GetChatEscalationAssignmentByIdUseCase(ChatEscalationAssignmentRepository repository) { this.repository = repository; }

    public ChatEscalationAssignmentResponse execute(ChatEscalationAssignmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundApplicationException(id.value().toString()));
        return new ChatEscalationAssignmentResponse(
                aggregate.id().value(),
                aggregate.escalationId().value(),
                aggregate.professionalId().value(),
                aggregate.assignedAt());
    }
}
