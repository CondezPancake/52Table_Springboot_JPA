package springboot.application.chatconversation.usecase;

import springboot.application.chatconversation.command.RegisterChatConversationCommand;
import springboot.application.chatconversation.dto.ChatConversationResponse;
import springboot.domain.chatconversation.model.aggregate.ChatConversation;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;

public class RegisterChatConversationUseCase {
    private final ChatConversationRepository repository;
    public RegisterChatConversationUseCase(ChatConversationRepository repository) { this.repository = repository; }

    public ChatConversationResponse execute(RegisterChatConversationCommand command) {
        ChatConversation aggregate = ChatConversation.register(
                command.conversationStatusId(),
                command.priorityId(),
                command.lastMessageAt(),
                command.closed(),
                command.closedAt(),
                command.closedBy());
        ChatConversation saved = repository.save(aggregate);
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
