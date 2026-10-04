package springboot.infrastructure.chatconversation.adapters.out.persistence.mappers;

import springboot.domain.chatconversation.model.aggregate.ChatConversation;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.priority.model.valueobject.PriorityId;
import springboot.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;

public class ChatConversationPersistenceMapper {
    public ChatConversationJpaEntity toJpa(ChatConversation domain) {
        if (domain == null) { return null; }
        ChatConversationJpaEntity jpa = new ChatConversationJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationStatusId(domain.conversationStatusId().value());
        jpa.setPriorityId(domain.priorityId().value());
        jpa.setLastMessageAt(domain.lastMessageAt());
        jpa.setClosed(domain.closed());
        jpa.setClosedAt(domain.closedAt());
        jpa.setClosedBy(domain.closedBy());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ChatConversation toDomain(ChatConversationJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatConversation.restore(
                new ChatConversationId(jpa.getId()),
                new ConversationStatusId(jpa.getConversationStatusId()),
                new PriorityId(jpa.getPriorityId()),
                jpa.getLastMessageAt(),
                jpa.isClosed(),
                jpa.getClosedAt(),
                jpa.getClosedBy(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
