package springboot.application.treatmentplan.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import springboot.domain.treatmentplan.event.TreatmentPlanDeletedEvent;
import springboot.domain.treatmentplan.model.aggregate.TreatmentPlan;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

class DeleteTreatmentPlanUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        TreatmentPlan aggregate = TreatmentPlan.register(
                EncounterId.generate(),
                ProfessionalId.generate(),
                "Initial treatment plan",
                "Treatment plan description",
                java.time.LocalDate.of(2026, 1, 10),
                java.time.LocalDate.of(2026, 6, 10),
                TreatmentStatusId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        TreatmentPlanDeletedEvent event = new DeleteTreatmentPlanUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(TreatmentPlanNotFoundApplicationException.class,
                () -> new DeleteTreatmentPlanUseCase(repository).execute(TreatmentPlanId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements TreatmentPlanRepository {
        private final TreatmentPlan aggregate; private TreatmentPlan deletedAggregate;
        private FakeRepository(TreatmentPlan aggregate) { this.aggregate = aggregate; }
        @Override public TreatmentPlan save(TreatmentPlan value) { return value; }
        @Override public Optional<TreatmentPlan> findById(TreatmentPlanId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<TreatmentPlan> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(TreatmentPlan value) { deletedAggregate = value; }
        private TreatmentPlan deletedAggregate() { return deletedAggregate; }
    }
}
