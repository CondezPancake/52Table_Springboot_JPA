package springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import springboot.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;
import springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers.ChatConversationAiSettingPersistenceMapper;

public class ChatConversationAiSettingRepositoryAdapter implements ChatConversationAiSettingRepository {
    private final ChatConversationAiSettingJpaRepository jpaRepository;
    private final ChatConversationAiSettingPersistenceMapper mapper;
    public ChatConversationAiSettingRepositoryAdapter(ChatConversationAiSettingJpaRepository jpaRepository, ChatConversationAiSettingPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatConversationAiSetting save(ChatConversationAiSetting aggregate) {
        ChatConversationAiSettingJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatConversationAiSetting> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatConversationAiSetting aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
