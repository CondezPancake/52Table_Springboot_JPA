package springboot.application.chatairun.usecase;

import springboot.application.chatairun.command.UpdateChatAiRunCommand;
import springboot.application.chatairun.dto.ChatAiRunResponse;
import springboot.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;

public class UpdateChatAiRunUseCase {
    private final ChatAiRunRepository repository;
    public UpdateChatAiRunUseCase(ChatAiRunRepository repository) { this.repository = repository; }

    public ChatAiRunResponse execute(UpdateChatAiRunCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.aiRunStatusId());
        var saved = repository.save(aggregate);
        return new ChatAiRunResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.messageId().value(),
                saved.modelId().value(),
                saved.aiRunStatusId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
