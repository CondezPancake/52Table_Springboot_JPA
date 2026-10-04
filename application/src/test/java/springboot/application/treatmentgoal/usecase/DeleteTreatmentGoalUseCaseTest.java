package springboot.application.treatmentgoal.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import springboot.domain.treatmentgoal.event.TreatmentGoalDeletedEvent;
import springboot.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;

class DeleteTreatmentGoalUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        TreatmentGoal aggregate = TreatmentGoal.register(
                TreatmentPlanId.generate(),
                "Reduce symptoms",
                java.time.LocalDate.of(2026, 6, 10),
                java.time.LocalDateTime.of(2026, 6, 10, 9, 0),
                "Progress notes",
                TreatmentGoalStatusId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        TreatmentGoalDeletedEvent event = new DeleteTreatmentGoalUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(TreatmentGoalNotFoundApplicationException.class,
                () -> new DeleteTreatmentGoalUseCase(repository).execute(TreatmentGoalId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements TreatmentGoalRepository {
        private final TreatmentGoal aggregate; private TreatmentGoal deletedAggregate;
        private FakeRepository(TreatmentGoal aggregate) { this.aggregate = aggregate; }
        @Override public TreatmentGoal save(TreatmentGoal value) { return value; }
        @Override public Optional<TreatmentGoal> findById(TreatmentGoalId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<TreatmentGoal> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(TreatmentGoal value) { deletedAggregate = value; }
        private TreatmentGoal deletedAggregate() { return deletedAggregate; }
    }
}
