package springboot.domain.chatairunmetric.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public interface ChatAiRunMetricRepository {
    ChatAiRunMetric save(ChatAiRunMetric aggregate);
    Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id);
    List<ChatAiRunMetric> findAll();

    void delete(ChatAiRunMetric aggregate);
}
