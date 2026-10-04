package springboot.domain.common.exception;

import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;

public class ChatParticipantNotFoundException extends RuntimeException {
    public ChatParticipantNotFoundException(ChatParticipantId id) {
        super("ChatParticipant not found with id: " + id.value());
    }
}
