package springboot.application.chatconversation.usecase;

import springboot.application.chatconversation.command.UpdateChatConversationCommand;
import springboot.application.chatconversation.dto.ChatConversationResponse;
import springboot.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;

public class UpdateChatConversationUseCase {
    private final ChatConversationRepository repository;
    public UpdateChatConversationUseCase(ChatConversationRepository repository) { this.repository = repository; }

    public ChatConversationResponse execute(UpdateChatConversationCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatConversationNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationStatusId(),
                command.priorityId(),
                command.lastMessageAt(),
                command.closed(),
                command.closedAt(),
                command.closedBy());
        var saved = repository.save(aggregate);
        return new ChatConversationResponse(
                saved.id().value(),
                saved.conversationStatusId().value(),
                saved.priorityId().value(),
                saved.lastMessageAt(),
                saved.closed(),
                saved.closedAt(),
                saved.closedBy(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
