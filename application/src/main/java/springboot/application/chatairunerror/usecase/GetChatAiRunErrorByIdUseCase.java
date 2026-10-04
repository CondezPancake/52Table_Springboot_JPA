package springboot.application.chatairunerror.usecase;

import springboot.application.chatairunerror.dto.ChatAiRunErrorResponse;
import springboot.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class GetChatAiRunErrorByIdUseCase {
    private final ChatAiRunErrorRepository repository;
    public GetChatAiRunErrorByIdUseCase(ChatAiRunErrorRepository repository) { this.repository = repository; }

    public ChatAiRunErrorResponse execute(ChatAiRunErrorId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunErrorNotFoundApplicationException(id.value().toString()));
        return new ChatAiRunErrorResponse(
                aggregate.id().value(),
                aggregate.aiRunId().value(),
                aggregate.errorMessage(),
                aggregate.errorCode(),
                aggregate.providerErrorId(),
                aggregate.createdAt());
    }
}
