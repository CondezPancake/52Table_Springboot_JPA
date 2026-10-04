package springboot.application.chatconversation.command;

import java.util.Objects;
import java.time.LocalDateTime;
import java.util.UUID;

import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.priority.model.valueobject.PriorityId;

public record UpdateChatConversationCommand(
        ChatConversationId id,
        ConversationStatusId conversationStatusId,
        PriorityId priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy
) {
    public UpdateChatConversationCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        Objects.requireNonNull(priorityId, "priorityId must not be null");
    }
}
