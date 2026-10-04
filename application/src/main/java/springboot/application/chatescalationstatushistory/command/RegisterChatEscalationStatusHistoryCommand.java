package springboot.application.chatescalationstatushistory.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record RegisterChatEscalationStatusHistoryCommand(
        ChatEscalationId escalationId,
        EscalationStatusId escalationStatusId,
        LocalDateTime changedAt
) {
    public RegisterChatEscalationStatusHistoryCommand {
        Objects.requireNonNull(escalationId, "escalationId must not be null");
        Objects.requireNonNull(escalationStatusId, "escalationStatusId must not be null");
        Objects.requireNonNull(changedAt, "changedAt must not be null");
    }
}
