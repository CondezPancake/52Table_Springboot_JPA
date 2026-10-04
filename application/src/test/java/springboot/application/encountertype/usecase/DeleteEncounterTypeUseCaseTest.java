package springboot.application.encountertype.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import springboot.domain.encountertype.event.EncounterTypeDeletedEvent;
import springboot.domain.encountertype.model.aggregate.EncounterType;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;


class DeleteEncounterTypeUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        EncounterType aggregate = EncounterType.register(
                "INITIAL",
                "Initial consultation",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        EncounterTypeDeletedEvent event = new DeleteEncounterTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(EncounterTypeNotFoundApplicationException.class,
                () -> new DeleteEncounterTypeUseCase(repository).execute(EncounterTypeId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements EncounterTypeRepository {
        private final EncounterType aggregate; private EncounterType deletedAggregate;
        private FakeRepository(EncounterType aggregate) { this.aggregate = aggregate; }
        @Override public EncounterType save(EncounterType value) { return value; }
        @Override public Optional<EncounterType> findById(EncounterTypeId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<EncounterType> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(EncounterType value) { deletedAggregate = value; }
        private EncounterType deletedAggregate() { return deletedAggregate; }
    }
}
