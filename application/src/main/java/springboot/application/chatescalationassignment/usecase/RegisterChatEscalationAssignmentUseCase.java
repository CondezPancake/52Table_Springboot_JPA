package springboot.application.chatescalationassignment.usecase;

import springboot.application.chatescalationassignment.command.RegisterChatEscalationAssignmentCommand;
import springboot.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import springboot.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class RegisterChatEscalationAssignmentUseCase {
    private final ChatEscalationAssignmentRepository repository;
    public RegisterChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) { this.repository = repository; }

    public ChatEscalationAssignmentResponse execute(RegisterChatEscalationAssignmentCommand command) {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(
                command.escalationId(),
                command.professionalId(),
                command.assignedAt());
        ChatEscalationAssignment saved = repository.save(aggregate);
        return new ChatEscalationAssignmentResponse(
                saved.id().value(),
                saved.escalationId().value(),
                saved.professionalId().value(),
                saved.assignedAt());
    }
}
