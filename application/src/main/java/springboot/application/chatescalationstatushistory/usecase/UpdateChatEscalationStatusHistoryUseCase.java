package springboot.application.chatescalationstatushistory.usecase;

import springboot.application.chatescalationstatushistory.command.UpdateChatEscalationStatusHistoryCommand;
import springboot.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import springboot.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class UpdateChatEscalationStatusHistoryUseCase {
    private final ChatEscalationStatusHistoryRepository repository;
    public UpdateChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) { this.repository = repository; }

    public ChatEscalationStatusHistoryResponse execute(UpdateChatEscalationStatusHistoryCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.escalationId(),
                command.escalationStatusId(),
                command.changedAt());
        var saved = repository.save(aggregate);
        return new ChatEscalationStatusHistoryResponse(
                saved.id().value(),
                saved.escalationId().value(),
                saved.escalationStatusId().value(),
                saved.changedAt(),
                saved.createdAt());
    }
}
