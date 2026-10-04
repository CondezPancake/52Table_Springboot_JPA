package springboot.application.medicationroute.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import springboot.domain.medicationroute.event.MedicationRouteDeletedEvent;
import springboot.domain.medicationroute.model.aggregate.MedicationRoute;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;
import springboot.domain.medicationroute.port.repository.MedicationRouteRepository;


class DeleteMedicationRouteUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        MedicationRoute aggregate = MedicationRoute.register(
                "ORAL",
                "Oral",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        MedicationRouteDeletedEvent event = new DeleteMedicationRouteUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MedicationRouteNotFoundApplicationException.class,
                () -> new DeleteMedicationRouteUseCase(repository).execute(MedicationRouteId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements MedicationRouteRepository {
        private final MedicationRoute aggregate; private MedicationRoute deletedAggregate;
        private FakeRepository(MedicationRoute aggregate) { this.aggregate = aggregate; }
        @Override public MedicationRoute save(MedicationRoute value) { return value; }
        @Override public Optional<MedicationRoute> findById(MedicationRouteId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<MedicationRoute> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(MedicationRoute value) { deletedAggregate = value; }
        private MedicationRoute deletedAggregate() { return deletedAggregate; }
    }
}
