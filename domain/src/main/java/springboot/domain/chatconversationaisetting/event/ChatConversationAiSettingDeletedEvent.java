package springboot.domain.chatconversationaisetting.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public record ChatConversationAiSettingDeletedEvent(
        ChatConversationAiSettingId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatConversationAiSettingDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
