package springboot.application.chatairunerror.usecase;

import springboot.application.chatairunerror.command.RegisterChatAiRunErrorCommand;
import springboot.application.chatairunerror.dto.ChatAiRunErrorResponse;
import springboot.domain.chatairunerror.model.aggregate.ChatAiRunError;
import springboot.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class RegisterChatAiRunErrorUseCase {
    private final ChatAiRunErrorRepository repository;
    public RegisterChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) { this.repository = repository; }

    public ChatAiRunErrorResponse execute(RegisterChatAiRunErrorCommand command) {
        ChatAiRunError aggregate = ChatAiRunError.register(
                command.aiRunId(),
                command.errorMessage(),
                command.errorCode(),
                command.providerErrorId());
        ChatAiRunError saved = repository.save(aggregate);
        return new ChatAiRunErrorResponse(
                saved.id().value(),
                saved.aiRunId().value(),
                saved.errorMessage(),
                saved.errorCode(),
                saved.providerErrorId(),
                saved.createdAt());
    }
}
