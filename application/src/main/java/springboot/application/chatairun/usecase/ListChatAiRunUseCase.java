package springboot.application.chatairun.usecase;

import java.util.List;

import springboot.application.chatairun.dto.ChatAiRunResponse;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;

public class ListChatAiRunUseCase {
    private final ChatAiRunRepository repository;
    public ListChatAiRunUseCase(ChatAiRunRepository repository) { this.repository = repository; }

    public List<ChatAiRunResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatAiRunResponse(
                                aggregate.id().value(),
                                aggregate.conversationId().value(),
                                aggregate.messageId().value(),
                                aggregate.modelId().value(),
                                aggregate.aiRunStatusId().value(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
