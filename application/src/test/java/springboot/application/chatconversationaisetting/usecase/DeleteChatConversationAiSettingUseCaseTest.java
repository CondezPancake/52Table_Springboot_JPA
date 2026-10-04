package springboot.application.chatconversationaisetting.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import springboot.domain.chatconversationaisetting.event.ChatConversationAiSettingDeletedEvent;
import springboot.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import springboot.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

class DeleteChatConversationAiSettingUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(
                ChatConversationId.generate(),
                true,
                AiModelId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        ChatConversationAiSettingDeletedEvent event = new DeleteChatConversationAiSettingUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatConversationAiSettingNotFoundApplicationException.class,
                () -> new DeleteChatConversationAiSettingUseCase(repository).execute(ChatConversationAiSettingId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatConversationAiSettingRepository {
        private final ChatConversationAiSetting aggregate; private ChatConversationAiSetting deletedAggregate;
        private FakeRepository(ChatConversationAiSetting aggregate) { this.aggregate = aggregate; }
        @Override public ChatConversationAiSetting save(ChatConversationAiSetting value) { return value; }
        @Override public Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatConversationAiSetting> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatConversationAiSetting value) { deletedAggregate = value; }
        private ChatConversationAiSetting deletedAggregate() { return deletedAggregate; }
    }
}
