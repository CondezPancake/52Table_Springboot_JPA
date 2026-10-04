package springboot.application.chatmessage.command;

import java.util.Objects;


import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;

public record UpdateChatMessageCommand(
        ChatMessageId id,
        ChatConversationId conversationId,
        MessageTypeId messageTypeId,
        ChatParticipantId participantId,
        String content,
        String metadata
) {
    public UpdateChatMessageCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageTypeId, "messageTypeId must not be null");
        Objects.requireNonNull(participantId, "participantId must not be null");
        Objects.requireNonNull(content, "content must not be null");
        Objects.requireNonNull(metadata, "metadata must not be null");
    }
}
