package springboot.infrastructure.messagetype.adapters.out.persistence.mappers;

import springboot.domain.messagetype.model.aggregate.MessageType;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;

import springboot.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;

public class MessageTypePersistenceMapper {
    public MessageTypeJpaEntity toJpa(MessageType domain) {
        if (domain == null) { return null; }
        MessageTypeJpaEntity jpa = new MessageTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameType(domain.nameType());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public MessageType toDomain(MessageTypeJpaEntity jpa) {
        if (jpa == null) { return null; }
        return MessageType.restore(
                new MessageTypeId(jpa.getId()),
                jpa.getNameType(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
