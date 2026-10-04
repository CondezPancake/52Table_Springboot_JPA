package springboot.infrastructure.chatairun.adapters.out.persistence.mappers;

import springboot.domain.chatairun.model.aggregate.ChatAiRun;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;

public class ChatAiRunPersistenceMapper {
    public ChatAiRunJpaEntity toJpa(ChatAiRun domain) {
        if (domain == null) { return null; }
        ChatAiRunJpaEntity jpa = new ChatAiRunJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setMessageId(domain.messageId().value());
        jpa.setModelId(domain.modelId().value());
        jpa.setAiRunStatusId(domain.aiRunStatusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ChatAiRun toDomain(ChatAiRunJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatAiRun.restore(
                new ChatAiRunId(jpa.getId()),
                new ChatConversationId(jpa.getConversationId()),
                new ChatMessageId(jpa.getMessageId()),
                new AiModelId(jpa.getModelId()),
                new AiRunStatusId(jpa.getAiRunStatusId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
