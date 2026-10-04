package springboot.application.chatescalation.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import springboot.domain.chatescalation.event.ChatEscalationDeletedEvent;
import springboot.domain.chatescalation.model.aggregate.ChatEscalation;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

class DeleteChatEscalationUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatEscalation aggregate = ChatEscalation.register(
                ChatConversationId.generate(),
                EscalationStatusId.generate(),
                true,
                "Requires professional review");
        FakeRepository repository = new FakeRepository(aggregate);
        ChatEscalationDeletedEvent event = new DeleteChatEscalationUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatEscalationNotFoundApplicationException.class,
                () -> new DeleteChatEscalationUseCase(repository).execute(ChatEscalationId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatEscalationRepository {
        private final ChatEscalation aggregate; private ChatEscalation deletedAggregate;
        private FakeRepository(ChatEscalation aggregate) { this.aggregate = aggregate; }
        @Override public ChatEscalation save(ChatEscalation value) { return value; }
        @Override public Optional<ChatEscalation> findById(ChatEscalationId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatEscalation> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatEscalation value) { deletedAggregate = value; }
        private ChatEscalation deletedAggregate() { return deletedAggregate; }
    }
}
