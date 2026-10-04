package springboot.domain.common.exception;

import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public class ChatAiRunMetricNotFoundException extends RuntimeException {
    public ChatAiRunMetricNotFoundException(ChatAiRunMetricId id) {
        super("ChatAiRunMetric not found with id: " + id.value());
    }
}
