package springboot.application.chatescalationassignment.usecase;

import springboot.application.chatescalationassignment.command.UpdateChatEscalationAssignmentCommand;
import springboot.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import springboot.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class UpdateChatEscalationAssignmentUseCase {
    private final ChatEscalationAssignmentRepository repository;
    public UpdateChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) { this.repository = repository; }

    public ChatEscalationAssignmentResponse execute(UpdateChatEscalationAssignmentCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.escalationId(),
                command.professionalId(),
                command.assignedAt());
        var saved = repository.save(aggregate);
        return new ChatEscalationAssignmentResponse(
                saved.id().value(),
                saved.escalationId().value(),
                saved.professionalId().value(),
                saved.assignedAt());
    }
}
