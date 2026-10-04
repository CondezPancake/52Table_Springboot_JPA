package springboot.application.chatconversation.usecase;

import java.util.List;

import springboot.application.chatconversation.dto.ChatConversationResponse;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;

public class ListChatConversationUseCase {
    private final ChatConversationRepository repository;
    public ListChatConversationUseCase(ChatConversationRepository repository) { this.repository = repository; }

    public List<ChatConversationResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatConversationResponse(
                                aggregate.id().value(),
                                aggregate.conversationStatusId().value(),
                                aggregate.priorityId().value(),
                                aggregate.lastMessageAt(),
                                aggregate.closed(),
                                aggregate.closedAt(),
                                aggregate.closedBy(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
