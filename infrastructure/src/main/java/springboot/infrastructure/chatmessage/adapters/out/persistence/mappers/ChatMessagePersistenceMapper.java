package springboot.infrastructure.chatmessage.adapters.out.persistence.mappers;

import springboot.domain.chatmessage.model.aggregate.ChatMessage;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;
import springboot.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;

public class ChatMessagePersistenceMapper {
    public ChatMessageJpaEntity toJpa(ChatMessage domain) {
        if (domain == null) { return null; }
        ChatMessageJpaEntity jpa = new ChatMessageJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setMessageTypeId(domain.messageTypeId().value());
        jpa.setParticipantId(domain.participantId().value());
        jpa.setContent(domain.content());
        jpa.setMetadata(domain.metadata());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public ChatMessage toDomain(ChatMessageJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatMessage.restore(
                new ChatMessageId(jpa.getId()),
                new ChatConversationId(jpa.getConversationId()),
                new MessageTypeId(jpa.getMessageTypeId()),
                new ChatParticipantId(jpa.getParticipantId()),
                jpa.getContent(),
                jpa.getMetadata(),
                jpa.getCreatedAt());
    }
}
