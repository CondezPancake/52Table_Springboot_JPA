package springboot.infrastructure.conversationstatus.adapters.out.persistence.mappers;

import springboot.domain.conversationstatus.model.aggregate.ConversationStatus;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;

import springboot.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;

public class ConversationStatusPersistenceMapper {
    public ConversationStatusJpaEntity toJpa(ConversationStatus domain) {
        if (domain == null) { return null; }
        ConversationStatusJpaEntity jpa = new ConversationStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameStatus(domain.nameStatus());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ConversationStatus toDomain(ConversationStatusJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ConversationStatus.restore(
                new ConversationStatusId(jpa.getId()),
                jpa.getNameStatus(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
