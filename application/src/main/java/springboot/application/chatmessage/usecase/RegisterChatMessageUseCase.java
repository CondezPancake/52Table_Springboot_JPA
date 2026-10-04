package springboot.application.chatmessage.usecase;

import springboot.application.chatmessage.command.RegisterChatMessageCommand;
import springboot.application.chatmessage.dto.ChatMessageResponse;
import springboot.domain.chatmessage.model.aggregate.ChatMessage;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;

public class RegisterChatMessageUseCase {
    private final ChatMessageRepository repository;
    public RegisterChatMessageUseCase(ChatMessageRepository repository) { this.repository = repository; }

    public ChatMessageResponse execute(RegisterChatMessageCommand command) {
        ChatMessage aggregate = ChatMessage.register(
                command.conversationId(),
                command.messageTypeId(),
                command.participantId(),
                command.content(),
                command.metadata());
        ChatMessage saved = repository.save(aggregate);
        return new ChatMessageResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.messageTypeId().value(),
                saved.participantId().value(),
                saved.content(),
                saved.metadata(),
                saved.createdAt());
    }
}
