package springboot.infrastructure.chatairunerror.adapters.out.persistence.mappers;

import springboot.domain.chatairunerror.model.aggregate.ChatAiRunError;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;

public class ChatAiRunErrorPersistenceMapper {
    public ChatAiRunErrorJpaEntity toJpa(ChatAiRunError domain) {
        if (domain == null) { return null; }
        ChatAiRunErrorJpaEntity jpa = new ChatAiRunErrorJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAiRunId(domain.aiRunId().value());
        jpa.setErrorMessage(domain.errorMessage());
        jpa.setErrorCode(domain.errorCode());
        jpa.setProviderErrorId(domain.providerErrorId());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public ChatAiRunError toDomain(ChatAiRunErrorJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatAiRunError.restore(
                new ChatAiRunErrorId(jpa.getId()),
                new ChatAiRunId(jpa.getAiRunId()),
                jpa.getErrorMessage(),
                jpa.getErrorCode(),
                jpa.getProviderErrorId(),
                jpa.getCreatedAt());
    }
}
