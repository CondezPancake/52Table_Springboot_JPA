package springboot.application.stateregion.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.stateregion.exception.StateRegionNotFoundApplicationException;
import springboot.domain.stateregion.event.StateRegionDeletedEvent;
import springboot.domain.stateregion.model.aggregate.StateRegion;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.domain.stateregion.port.repository.StateRegionRepository;
import springboot.domain.country.model.valueobject.CountryId;

class DeleteStateRegionUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        StateRegion aggregate = StateRegion.register(
                "Cundinamarca",
                "CUN",
                "Central region",
                true,
                CountryId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        StateRegionDeletedEvent event = new DeleteStateRegionUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(StateRegionNotFoundApplicationException.class,
                () -> new DeleteStateRegionUseCase(repository).execute(StateRegionId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements StateRegionRepository {
        private final StateRegion aggregate; private StateRegion deletedAggregate;
        private FakeRepository(StateRegion aggregate) { this.aggregate = aggregate; }
        @Override public StateRegion save(StateRegion value) { return value; }
        @Override public Optional<StateRegion> findById(StateRegionId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<StateRegion> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(StateRegion value) { deletedAggregate = value; }
        private StateRegion deletedAggregate() { return deletedAggregate; }
    }
}
