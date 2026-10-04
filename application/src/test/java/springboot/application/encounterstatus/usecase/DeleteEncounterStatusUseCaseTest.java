package springboot.application.encounterstatus.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import springboot.domain.encounterstatus.event.EncounterStatusDeletedEvent;
import springboot.domain.encounterstatus.model.aggregate.EncounterStatus;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;


class DeleteEncounterStatusUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        EncounterStatus aggregate = EncounterStatus.register(
                "SCHEDULED",
                "Scheduled",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        EncounterStatusDeletedEvent event = new DeleteEncounterStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(EncounterStatusNotFoundApplicationException.class,
                () -> new DeleteEncounterStatusUseCase(repository).execute(EncounterStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements EncounterStatusRepository {
        private final EncounterStatus aggregate; private EncounterStatus deletedAggregate;
        private FakeRepository(EncounterStatus aggregate) { this.aggregate = aggregate; }
        @Override public EncounterStatus save(EncounterStatus value) { return value; }
        @Override public Optional<EncounterStatus> findById(EncounterStatusId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<EncounterStatus> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(EncounterStatus value) { deletedAggregate = value; }
        private EncounterStatus deletedAggregate() { return deletedAggregate; }
    }
}
