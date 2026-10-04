package springboot.domain.chatconversationaisetting.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public interface ChatConversationAiSettingRepository {
    ChatConversationAiSetting save(ChatConversationAiSetting aggregate);
    Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id);
    List<ChatConversationAiSetting> findAll();

    void delete(ChatConversationAiSetting aggregate);
}
