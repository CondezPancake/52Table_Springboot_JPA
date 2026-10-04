package springboot.application.conversationstatus.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import springboot.domain.conversationstatus.event.ConversationStatusDeletedEvent;
import springboot.domain.conversationstatus.model.aggregate.ConversationStatus;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;


class DeleteConversationStatusUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ConversationStatus aggregate = ConversationStatus.register(
                "Open");
        FakeRepository repository = new FakeRepository(aggregate);
        ConversationStatusDeletedEvent event = new DeleteConversationStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ConversationStatusNotFoundApplicationException.class,
                () -> new DeleteConversationStatusUseCase(repository).execute(ConversationStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ConversationStatusRepository {
        private final ConversationStatus aggregate; private ConversationStatus deletedAggregate;
        private FakeRepository(ConversationStatus aggregate) { this.aggregate = aggregate; }
        @Override public ConversationStatus save(ConversationStatus value) { return value; }
        @Override public Optional<ConversationStatus> findById(ConversationStatusId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ConversationStatus> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ConversationStatus value) { deletedAggregate = value; }
        private ConversationStatus deletedAggregate() { return deletedAggregate; }
    }
}
