package springboot.application.chatairunerror.usecase;

import springboot.application.chatairunerror.command.UpdateChatAiRunErrorCommand;
import springboot.application.chatairunerror.dto.ChatAiRunErrorResponse;
import springboot.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import springboot.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class UpdateChatAiRunErrorUseCase {
    private final ChatAiRunErrorRepository repository;
    public UpdateChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) { this.repository = repository; }

    public ChatAiRunErrorResponse execute(UpdateChatAiRunErrorCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunErrorNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.aiRunId(),
                command.errorMessage(),
                command.errorCode(),
                command.providerErrorId());
        var saved = repository.save(aggregate);
        return new ChatAiRunErrorResponse(
                saved.id().value(),
                saved.aiRunId().value(),
                saved.errorMessage(),
                saved.errorCode(),
                saved.providerErrorId(),
                saved.createdAt());
    }
}
