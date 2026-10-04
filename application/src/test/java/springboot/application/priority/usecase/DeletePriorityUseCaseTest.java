package springboot.application.priority.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.priority.exception.PriorityNotFoundApplicationException;
import springboot.domain.priority.event.PriorityDeletedEvent;
import springboot.domain.priority.model.aggregate.Priority;
import springboot.domain.priority.model.valueobject.PriorityId;
import springboot.domain.priority.port.repository.PriorityRepository;


class DeletePriorityUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        Priority aggregate = Priority.register(
                "High");
        FakeRepository repository = new FakeRepository(aggregate);
        PriorityDeletedEvent event = new DeletePriorityUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(PriorityNotFoundApplicationException.class,
                () -> new DeletePriorityUseCase(repository).execute(PriorityId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements PriorityRepository {
        private final Priority aggregate; private Priority deletedAggregate;
        private FakeRepository(Priority aggregate) { this.aggregate = aggregate; }
        @Override public Priority save(Priority value) { return value; }
        @Override public Optional<Priority> findById(PriorityId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<Priority> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(Priority value) { deletedAggregate = value; }
        private Priority deletedAggregate() { return deletedAggregate; }
    }
}
