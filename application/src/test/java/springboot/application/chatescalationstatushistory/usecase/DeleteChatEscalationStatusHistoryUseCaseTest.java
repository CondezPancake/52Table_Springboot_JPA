package springboot.application.chatescalationstatushistory.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import springboot.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryDeletedEvent;
import springboot.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

class DeleteChatEscalationStatusHistoryUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(
                ChatEscalationId.generate(),
                EscalationStatusId.generate(),
                java.time.LocalDateTime.of(2026, 1, 10, 10, 30));
        FakeRepository repository = new FakeRepository(aggregate);
        ChatEscalationStatusHistoryDeletedEvent event = new DeleteChatEscalationStatusHistoryUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatEscalationStatusHistoryNotFoundApplicationException.class,
                () -> new DeleteChatEscalationStatusHistoryUseCase(repository).execute(ChatEscalationStatusHistoryId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatEscalationStatusHistoryRepository {
        private final ChatEscalationStatusHistory aggregate; private ChatEscalationStatusHistory deletedAggregate;
        private FakeRepository(ChatEscalationStatusHistory aggregate) { this.aggregate = aggregate; }
        @Override public ChatEscalationStatusHistory save(ChatEscalationStatusHistory value) { return value; }
        @Override public Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatEscalationStatusHistory> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatEscalationStatusHistory value) { deletedAggregate = value; }
        private ChatEscalationStatusHistory deletedAggregate() { return deletedAggregate; }
    }
}
