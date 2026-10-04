package springboot.infrastructure.chatairun.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatairun.model.aggregate.ChatAiRun;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;
import springboot.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;
import springboot.infrastructure.chatairun.adapters.out.persistence.mappers.ChatAiRunPersistenceMapper;

public class ChatAiRunRepositoryAdapter implements ChatAiRunRepository {
    private final ChatAiRunJpaRepository jpaRepository;
    private final ChatAiRunPersistenceMapper mapper;
    public ChatAiRunRepositoryAdapter(ChatAiRunJpaRepository jpaRepository, ChatAiRunPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatAiRun save(ChatAiRun aggregate) {
        ChatAiRunJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatAiRun> findById(ChatAiRunId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatAiRun> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatAiRun aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
