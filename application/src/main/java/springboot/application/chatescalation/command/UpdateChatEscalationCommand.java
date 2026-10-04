package springboot.application.chatescalation.command;

import java.util.Objects;


import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record UpdateChatEscalationCommand(
        ChatEscalationId id,
        ChatConversationId conversationId,
        EscalationStatusId statusId,
        boolean fromAi,
        String reason
) {
    public UpdateChatEscalationCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(reason, "reason must not be null");
    }
}
