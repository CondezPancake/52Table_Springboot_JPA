package springboot.application.chatconversationaisetting.usecase;

import java.time.LocalDateTime;

import springboot.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import springboot.domain.chatconversationaisetting.event.ChatConversationAiSettingDeletedEvent;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import springboot.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class DeleteChatConversationAiSettingUseCase {
    private final ChatConversationAiSettingRepository repository;
    public DeleteChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) { this.repository = repository; }

    public ChatConversationAiSettingDeletedEvent execute(ChatConversationAiSettingId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatConversationAiSettingDeletedEvent(id, LocalDateTime.now());
    }
}
