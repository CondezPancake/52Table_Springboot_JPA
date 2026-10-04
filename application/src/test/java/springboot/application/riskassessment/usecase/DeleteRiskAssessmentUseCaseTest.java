package springboot.application.riskassessment.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import springboot.domain.riskassessment.event.RiskAssessmentDeletedEvent;
import springboot.domain.riskassessment.model.aggregate.RiskAssessment;
import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;
import springboot.domain.riskassessment.port.repository.RiskAssessmentRepository;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;

class DeleteRiskAssessmentUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        RiskAssessment aggregate = RiskAssessment.register(
                EncounterId.generate(),
                RiskLevelId.generate(),
                false,
                false,
                false,
                false,
                false,
                "No acute factors",
                "Family support",
                "Continue monitoring",
                "Stable",
                java.time.LocalDateTime.of(2026, 1, 10, 8, 0),
                ProfessionalId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        RiskAssessmentDeletedEvent event = new DeleteRiskAssessmentUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(RiskAssessmentNotFoundApplicationException.class,
                () -> new DeleteRiskAssessmentUseCase(repository).execute(RiskAssessmentId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements RiskAssessmentRepository {
        private final RiskAssessment aggregate; private RiskAssessment deletedAggregate;
        private FakeRepository(RiskAssessment aggregate) { this.aggregate = aggregate; }
        @Override public RiskAssessment save(RiskAssessment value) { return value; }
        @Override public Optional<RiskAssessment> findById(RiskAssessmentId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<RiskAssessment> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(RiskAssessment value) { deletedAggregate = value; }
        private RiskAssessment deletedAggregate() { return deletedAggregate; }
    }
}
