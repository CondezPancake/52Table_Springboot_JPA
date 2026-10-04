package springboot.application.chatairun.usecase;

import springboot.application.chatairun.command.RegisterChatAiRunCommand;
import springboot.application.chatairun.dto.ChatAiRunResponse;
import springboot.domain.chatairun.model.aggregate.ChatAiRun;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;

public class RegisterChatAiRunUseCase {
    private final ChatAiRunRepository repository;
    public RegisterChatAiRunUseCase(ChatAiRunRepository repository) { this.repository = repository; }

    public ChatAiRunResponse execute(RegisterChatAiRunCommand command) {
        ChatAiRun aggregate = ChatAiRun.register(
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.aiRunStatusId());
        ChatAiRun saved = repository.save(aggregate);
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
