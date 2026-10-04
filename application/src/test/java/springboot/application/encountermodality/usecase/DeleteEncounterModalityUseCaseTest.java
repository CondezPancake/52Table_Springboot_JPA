package springboot.application.encountermodality.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import springboot.domain.encountermodality.event.EncounterModalityDeletedEvent;
import springboot.domain.encountermodality.model.aggregate.EncounterModality;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;


class DeleteEncounterModalityUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        EncounterModality aggregate = EncounterModality.register(
                "IN_PERSON",
                "In person",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        EncounterModalityDeletedEvent event = new DeleteEncounterModalityUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(EncounterModalityNotFoundApplicationException.class,
                () -> new DeleteEncounterModalityUseCase(repository).execute(EncounterModalityId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements EncounterModalityRepository {
        private final EncounterModality aggregate; private EncounterModality deletedAggregate;
        private FakeRepository(EncounterModality aggregate) { this.aggregate = aggregate; }
        @Override public EncounterModality save(EncounterModality value) { return value; }
        @Override public Optional<EncounterModality> findById(EncounterModalityId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<EncounterModality> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(EncounterModality value) { deletedAggregate = value; }
        private EncounterModality deletedAggregate() { return deletedAggregate; }
    }
}
