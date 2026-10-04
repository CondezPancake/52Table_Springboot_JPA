package springboot.infrastructure.chatconversation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatconversation.model.aggregate.ChatConversation;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;
import springboot.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;
import springboot.infrastructure.chatconversation.adapters.out.persistence.mappers.ChatConversationPersistenceMapper;

public class ChatConversationRepositoryAdapter implements ChatConversationRepository {
    private final ChatConversationJpaRepository jpaRepository;
    private final ChatConversationPersistenceMapper mapper;
    public ChatConversationRepositoryAdapter(ChatConversationJpaRepository jpaRepository, ChatConversationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatConversation save(ChatConversation aggregate) {
        ChatConversationJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatConversation> findById(ChatConversationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatConversation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatConversation aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
