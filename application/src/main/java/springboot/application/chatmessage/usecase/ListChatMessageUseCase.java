package springboot.application.chatmessage.usecase;

import java.util.List;

import springboot.application.chatmessage.dto.ChatMessageResponse;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;

public class ListChatMessageUseCase {
    private final ChatMessageRepository repository;
    public ListChatMessageUseCase(ChatMessageRepository repository) { this.repository = repository; }

    public List<ChatMessageResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatMessageResponse(
                                aggregate.id().value(),
                                aggregate.conversationId().value(),
                                aggregate.messageTypeId().value(),
                                aggregate.participantId().value(),
                                aggregate.content(),
                                aggregate.metadata(),
                                aggregate.createdAt()))
                .toList();
    }
}
