package springboot.application.sendertype.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import springboot.domain.sendertype.event.SenderTypeDeletedEvent;
import springboot.domain.sendertype.model.aggregate.SenderType;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;


class DeleteSenderTypeUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        SenderType aggregate = SenderType.register(
                "Patient");
        FakeRepository repository = new FakeRepository(aggregate);
        SenderTypeDeletedEvent event = new DeleteSenderTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SenderTypeNotFoundApplicationException.class,
                () -> new DeleteSenderTypeUseCase(repository).execute(SenderTypeId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements SenderTypeRepository {
        private final SenderType aggregate; private SenderType deletedAggregate;
        private FakeRepository(SenderType aggregate) { this.aggregate = aggregate; }
        @Override public SenderType save(SenderType value) { return value; }
        @Override public Optional<SenderType> findById(SenderTypeId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<SenderType> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(SenderType value) { deletedAggregate = value; }
        private SenderType deletedAggregate() { return deletedAggregate; }
    }
}
