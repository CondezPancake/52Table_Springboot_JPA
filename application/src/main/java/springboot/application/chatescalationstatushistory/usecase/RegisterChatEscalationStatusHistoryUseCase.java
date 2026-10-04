package springboot.application.chatescalationstatushistory.usecase;

import springboot.application.chatescalationstatushistory.command.RegisterChatEscalationStatusHistoryCommand;
import springboot.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import springboot.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class RegisterChatEscalationStatusHistoryUseCase {
    private final ChatEscalationStatusHistoryRepository repository;
    public RegisterChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) { this.repository = repository; }

    public ChatEscalationStatusHistoryResponse execute(RegisterChatEscalationStatusHistoryCommand command) {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(
                command.escalationId(),
                command.escalationStatusId(),
                command.changedAt());
        ChatEscalationStatusHistory saved = repository.save(aggregate);
        return new ChatEscalationStatusHistoryResponse(
                saved.id().value(),
                saved.escalationId().value(),
                saved.escalationStatusId().value(),
                saved.changedAt(),
                saved.createdAt());
    }
}
