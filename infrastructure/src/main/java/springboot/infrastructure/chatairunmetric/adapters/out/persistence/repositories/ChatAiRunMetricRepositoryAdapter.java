package springboot.infrastructure.chatairunmetric.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import springboot.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;
import springboot.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;
import springboot.infrastructure.chatairunmetric.adapters.out.persistence.mappers.ChatAiRunMetricPersistenceMapper;

public class ChatAiRunMetricRepositoryAdapter implements ChatAiRunMetricRepository {
    private final ChatAiRunMetricJpaRepository jpaRepository;
    private final ChatAiRunMetricPersistenceMapper mapper;
    public ChatAiRunMetricRepositoryAdapter(ChatAiRunMetricJpaRepository jpaRepository, ChatAiRunMetricPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatAiRunMetric save(ChatAiRunMetric aggregate) {
        ChatAiRunMetricJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatAiRunMetric> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatAiRunMetric aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
