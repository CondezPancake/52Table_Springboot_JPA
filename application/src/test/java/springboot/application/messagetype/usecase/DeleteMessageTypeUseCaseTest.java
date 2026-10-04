package springboot.application.messagetype.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import springboot.domain.messagetype.event.MessageTypeDeletedEvent;
import springboot.domain.messagetype.model.aggregate.MessageType;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;


class DeleteMessageTypeUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        MessageType aggregate = MessageType.register(
                "Text");
        FakeRepository repository = new FakeRepository(aggregate);
        MessageTypeDeletedEvent event = new DeleteMessageTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MessageTypeNotFoundApplicationException.class,
                () -> new DeleteMessageTypeUseCase(repository).execute(MessageTypeId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements MessageTypeRepository {
        private final MessageType aggregate; private MessageType deletedAggregate;
        private FakeRepository(MessageType aggregate) { this.aggregate = aggregate; }
        @Override public MessageType save(MessageType value) { return value; }
        @Override public Optional<MessageType> findById(MessageTypeId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<MessageType> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(MessageType value) { deletedAggregate = value; }
        private MessageType deletedAggregate() { return deletedAggregate; }
    }
}
