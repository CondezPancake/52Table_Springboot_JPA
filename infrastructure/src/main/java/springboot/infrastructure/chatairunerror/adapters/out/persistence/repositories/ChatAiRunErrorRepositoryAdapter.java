package springboot.infrastructure.chatairunerror.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatairunerror.model.aggregate.ChatAiRunError;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;
import springboot.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;
import springboot.infrastructure.chatairunerror.adapters.out.persistence.mappers.ChatAiRunErrorPersistenceMapper;

public class ChatAiRunErrorRepositoryAdapter implements ChatAiRunErrorRepository {
    private final ChatAiRunErrorJpaRepository jpaRepository;
    private final ChatAiRunErrorPersistenceMapper mapper;
    public ChatAiRunErrorRepositoryAdapter(ChatAiRunErrorJpaRepository jpaRepository, ChatAiRunErrorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatAiRunError save(ChatAiRunError aggregate) {
        ChatAiRunErrorJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatAiRunError> findById(ChatAiRunErrorId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatAiRunError> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatAiRunError aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
