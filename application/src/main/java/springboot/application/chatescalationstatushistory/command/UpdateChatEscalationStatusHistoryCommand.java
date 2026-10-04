package springboot.application.chatescalationstatushistory.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record UpdateChatEscalationStatusHistoryCommand(
        ChatEscalationStatusHistoryId id,
        ChatEscalationId escalationId,
        EscalationStatusId escalationStatusId,
        LocalDateTime changedAt
) {
    public UpdateChatEscalationStatusHistoryCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(escalationId, "escalationId must not be null");
        Objects.requireNonNull(escalationStatusId, "escalationStatusId must not be null");
        Objects.requireNonNull(changedAt, "changedAt must not be null");
    }
}
