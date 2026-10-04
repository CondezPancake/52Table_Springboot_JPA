package springboot.infrastructure.chatmessage.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatmessage.model.aggregate.ChatMessage;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;
import springboot.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;
import springboot.infrastructure.chatmessage.adapters.out.persistence.mappers.ChatMessagePersistenceMapper;

public class ChatMessageRepositoryAdapter implements ChatMessageRepository {
    private final ChatMessageJpaRepository jpaRepository;
    private final ChatMessagePersistenceMapper mapper;
    public ChatMessageRepositoryAdapter(ChatMessageJpaRepository jpaRepository, ChatMessagePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatMessage save(ChatMessage aggregate) {
        ChatMessageJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatMessage> findById(ChatMessageId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatMessage> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatMessage aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
