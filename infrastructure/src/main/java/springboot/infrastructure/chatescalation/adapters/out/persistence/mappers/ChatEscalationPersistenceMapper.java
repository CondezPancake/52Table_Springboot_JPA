package springboot.infrastructure.chatescalation.adapters.out.persistence.mappers;

import springboot.domain.chatescalation.model.aggregate.ChatEscalation;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;
import springboot.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;

public class ChatEscalationPersistenceMapper {
    public ChatEscalationJpaEntity toJpa(ChatEscalation domain) {
        if (domain == null) { return null; }
        ChatEscalationJpaEntity jpa = new ChatEscalationJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setStatusId(domain.statusId().value());
        jpa.setFromAi(domain.fromAi());
        jpa.setReason(domain.reason());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public ChatEscalation toDomain(ChatEscalationJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatEscalation.restore(
                new ChatEscalationId(jpa.getId()),
                new ChatConversationId(jpa.getConversationId()),
                new EscalationStatusId(jpa.getStatusId()),
                jpa.isFromAi(),
                jpa.getReason(),
                jpa.getCreatedAt());
    }
}
