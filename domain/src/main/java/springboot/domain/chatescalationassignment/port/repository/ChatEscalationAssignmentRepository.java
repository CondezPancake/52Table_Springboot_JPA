package springboot.domain.chatescalationassignment.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public interface ChatEscalationAssignmentRepository {
    ChatEscalationAssignment save(ChatEscalationAssignment aggregate);
    Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id);
    List<ChatEscalationAssignment> findAll();

    void delete(ChatEscalationAssignment aggregate);
}
