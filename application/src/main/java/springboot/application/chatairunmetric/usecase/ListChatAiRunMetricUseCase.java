package springboot.application.chatairunmetric.usecase;

import java.util.List;

import springboot.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import springboot.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class ListChatAiRunMetricUseCase {
    private final ChatAiRunMetricRepository repository;
    public ListChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) { this.repository = repository; }

    public List<ChatAiRunMetricResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatAiRunMetricResponse(
                                aggregate.id().value(),
                                aggregate.aiRunId().value(),
                                aggregate.promptTokens(),
                                aggregate.completionTokens(),
                                aggregate.totalTokens(),
                                aggregate.cost(),
                                aggregate.createdAt()))
                .toList();
    }
}
