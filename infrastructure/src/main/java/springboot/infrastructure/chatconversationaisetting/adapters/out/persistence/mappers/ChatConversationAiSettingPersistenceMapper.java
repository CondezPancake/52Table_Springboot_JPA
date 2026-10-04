package springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers;

import springboot.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

public class ChatConversationAiSettingPersistenceMapper {
    public ChatConversationAiSettingJpaEntity toJpa(ChatConversationAiSetting domain) {
        if (domain == null) { return null; }
        ChatConversationAiSettingJpaEntity jpa = new ChatConversationAiSettingJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setAiEnabled(domain.aiEnabled());
        jpa.setDefaultModelId(domain.defaultModelId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ChatConversationAiSetting toDomain(ChatConversationAiSettingJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatConversationAiSetting.restore(
                new ChatConversationAiSettingId(jpa.getId()),
                new ChatConversationId(jpa.getConversationId()),
                jpa.isAiEnabled(),
                new AiModelId(jpa.getDefaultModelId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
