package springboot.application.chatconversationaisetting.usecase;

import java.util.List;

import springboot.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import springboot.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class ListChatConversationAiSettingUseCase {
    private final ChatConversationAiSettingRepository repository;
    public ListChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) { this.repository = repository; }

    public List<ChatConversationAiSettingResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatConversationAiSettingResponse(
                                aggregate.id().value(),
                                aggregate.conversationId().value(),
                                aggregate.aiEnabled(),
                                aggregate.defaultModelId().value(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
