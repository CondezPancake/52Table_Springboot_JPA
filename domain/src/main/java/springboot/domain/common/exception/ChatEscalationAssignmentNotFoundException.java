package springboot.domain.common.exception;

import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public class ChatEscalationAssignmentNotFoundException extends RuntimeException {
    public ChatEscalationAssignmentNotFoundException(ChatEscalationAssignmentId id) {
        super("ChatEscalationAssignment not found with id: " + id.value());
    }
}
