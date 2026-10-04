package springboot.application.chatescalationassignment.usecase;

import java.time.LocalDateTime;

import springboot.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import springboot.domain.chatescalationassignment.event.ChatEscalationAssignmentDeletedEvent;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class DeleteChatEscalationAssignmentUseCase {
    private final ChatEscalationAssignmentRepository repository;
    public DeleteChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) { this.repository = repository; }

    public ChatEscalationAssignmentDeletedEvent execute(ChatEscalationAssignmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatEscalationAssignmentDeletedEvent(id, LocalDateTime.now());
    }
}
