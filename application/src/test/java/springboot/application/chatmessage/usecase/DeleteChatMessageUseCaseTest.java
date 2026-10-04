package springboot.application.chatmessage.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import springboot.domain.chatmessage.event.ChatMessageDeletedEvent;
import springboot.domain.chatmessage.model.aggregate.ChatMessage;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;

class DeleteChatMessageUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatMessage aggregate = ChatMessage.register(
                ChatConversationId.generate(),
                MessageTypeId.generate(),
                ChatParticipantId.generate(),
                "{\"text\":\"Hello\"}",
                "{}");
        FakeRepository repository = new FakeRepository(aggregate);
        ChatMessageDeletedEvent event = new DeleteChatMessageUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatMessageNotFoundApplicationException.class,
                () -> new DeleteChatMessageUseCase(repository).execute(ChatMessageId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatMessageRepository {
        private final ChatMessage aggregate; private ChatMessage deletedAggregate;
        private FakeRepository(ChatMessage aggregate) { this.aggregate = aggregate; }
        @Override public ChatMessage save(ChatMessage value) { return value; }
        @Override public Optional<ChatMessage> findById(ChatMessageId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatMessage> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatMessage value) { deletedAggregate = value; }
        private ChatMessage deletedAggregate() { return deletedAggregate; }
    }
}
