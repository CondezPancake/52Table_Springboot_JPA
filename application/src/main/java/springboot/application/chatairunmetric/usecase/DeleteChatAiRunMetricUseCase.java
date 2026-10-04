package springboot.application.chatairunmetric.usecase;

import java.time.LocalDateTime;

import springboot.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import springboot.domain.chatairunmetric.event.ChatAiRunMetricDeletedEvent;
import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import springboot.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class DeleteChatAiRunMetricUseCase {
    private final ChatAiRunMetricRepository repository;
    public DeleteChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) { this.repository = repository; }

    public ChatAiRunMetricDeletedEvent execute(ChatAiRunMetricId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunMetricNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatAiRunMetricDeletedEvent(id, LocalDateTime.now());
    }
}
