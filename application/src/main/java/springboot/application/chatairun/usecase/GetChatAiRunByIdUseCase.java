package springboot.application.chatairun.usecase;

import springboot.application.chatairun.dto.ChatAiRunResponse;
import springboot.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;

public class GetChatAiRunByIdUseCase {
    private final ChatAiRunRepository repository;
    public GetChatAiRunByIdUseCase(ChatAiRunRepository repository) { this.repository = repository; }

    public ChatAiRunResponse execute(ChatAiRunId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunNotFoundApplicationException(id.value().toString()));
        return new ChatAiRunResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.messageId().value(),
                aggregate.modelId().value(),
                aggregate.aiRunStatusId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
