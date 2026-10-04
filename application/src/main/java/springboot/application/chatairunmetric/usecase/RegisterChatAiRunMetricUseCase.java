package springboot.application.chatairunmetric.usecase;

import springboot.application.chatairunmetric.command.RegisterChatAiRunMetricCommand;
import springboot.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import springboot.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import springboot.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class RegisterChatAiRunMetricUseCase {
    private final ChatAiRunMetricRepository repository;
    public RegisterChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) { this.repository = repository; }

    public ChatAiRunMetricResponse execute(RegisterChatAiRunMetricCommand command) {
        ChatAiRunMetric aggregate = ChatAiRunMetric.register(
                command.aiRunId(),
                command.promptTokens(),
                command.completionTokens(),
                command.totalTokens(),
                command.cost());
        ChatAiRunMetric saved = repository.save(aggregate);
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
