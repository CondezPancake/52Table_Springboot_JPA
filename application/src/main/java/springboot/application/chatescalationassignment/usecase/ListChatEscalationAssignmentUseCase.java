package springboot.application.chatescalationassignment.usecase;

import java.util.List;

import springboot.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class ListChatEscalationAssignmentUseCase {
    private final ChatEscalationAssignmentRepository repository;
    public ListChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) { this.repository = repository; }

    public List<ChatEscalationAssignmentResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatEscalationAssignmentResponse(
                                aggregate.id().value(),
                                aggregate.escalationId().value(),
                                aggregate.professionalId().value(),
                                aggregate.assignedAt()))
                .toList();
    }
}
