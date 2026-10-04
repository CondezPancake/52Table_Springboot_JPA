package springboot.application.chatconversation.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import springboot.domain.chatconversation.event.ChatConversationDeletedEvent;
import springboot.domain.chatconversation.model.aggregate.ChatConversation;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.priority.model.valueobject.PriorityId;

class DeleteChatConversationUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatConversation aggregate = ChatConversation.register(
                ConversationStatusId.generate(),
                PriorityId.generate(),
                null,
                null,
                null,
                null);
        FakeRepository repository = new FakeRepository(aggregate);
        ChatConversationDeletedEvent event = new DeleteChatConversationUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatConversationNotFoundApplicationException.class,
                () -> new DeleteChatConversationUseCase(repository).execute(ChatConversationId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatConversationRepository {
        private final ChatConversation aggregate; private ChatConversation deletedAggregate;
        private FakeRepository(ChatConversation aggregate) { this.aggregate = aggregate; }
        @Override public ChatConversation save(ChatConversation value) { return value; }
        @Override public Optional<ChatConversation> findById(ChatConversationId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatConversation> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatConversation value) { deletedAggregate = value; }
        private ChatConversation deletedAggregate() { return deletedAggregate; }
    }
}
