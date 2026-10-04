package springboot.application.chatairunerror.usecase;

import java.util.List;

import springboot.application.chatairunerror.dto.ChatAiRunErrorResponse;
import springboot.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class ListChatAiRunErrorUseCase {
    private final ChatAiRunErrorRepository repository;
    public ListChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) { this.repository = repository; }

    public List<ChatAiRunErrorResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatAiRunErrorResponse(
                                aggregate.id().value(),
                                aggregate.aiRunId().value(),
                                aggregate.errorMessage(),
                                aggregate.errorCode(),
                                aggregate.providerErrorId(),
                                aggregate.createdAt()))
                .toList();
    }
}
