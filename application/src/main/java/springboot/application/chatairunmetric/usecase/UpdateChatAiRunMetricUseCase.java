package springboot.application.chatairunmetric.usecase;

import springboot.application.chatairunmetric.command.UpdateChatAiRunMetricCommand;
import springboot.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import springboot.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import springboot.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class UpdateChatAiRunMetricUseCase {
    private final ChatAiRunMetricRepository repository;
    public UpdateChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) { this.repository = repository; }

    public ChatAiRunMetricResponse execute(UpdateChatAiRunMetricCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunMetricNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.aiRunId(),
                command.promptTokens(),
                command.completionTokens(),
                command.totalTokens(),
                command.cost());
        var saved = repository.save(aggregate);
        return new ChatAiRunMetricResponse(
                saved.id().value(),
                saved.aiRunId().value(),
                saved.promptTokens(),
                saved.completionTokens(),
                saved.totalTokens(),
                saved.cost(),
                saved.createdAt());
    }
}
