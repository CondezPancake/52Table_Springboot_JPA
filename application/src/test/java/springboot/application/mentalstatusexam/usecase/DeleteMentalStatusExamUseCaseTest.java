package springboot.application.mentalstatusexam.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import springboot.domain.mentalstatusexam.event.MentalStatusExamDeletedEvent;
import springboot.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import springboot.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class DeleteMentalStatusExamUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        MentalStatusExam aggregate = MentalStatusExam.register(
                EncounterId.generate(),
                "Appropriate",
                "Cooperative",
                "Open",
                "Alert",
                "Oriented",
                "Sustained",
                "Intact",
                "Clear",
                "Stable",
                "Congruent",
                "Logical",
                "Appropriate",
                "No alterations",
                "Preserved",
                "Present",
                "Normal",
                "No additional findings",
                ProfessionalId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        MentalStatusExamDeletedEvent event = new DeleteMentalStatusExamUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MentalStatusExamNotFoundApplicationException.class,
                () -> new DeleteMentalStatusExamUseCase(repository).execute(MentalStatusExamId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements MentalStatusExamRepository {
        private final MentalStatusExam aggregate; private MentalStatusExam deletedAggregate;
        private FakeRepository(MentalStatusExam aggregate) { this.aggregate = aggregate; }
        @Override public MentalStatusExam save(MentalStatusExam value) { return value; }
        @Override public Optional<MentalStatusExam> findById(MentalStatusExamId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<MentalStatusExam> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(MentalStatusExam value) { deletedAggregate = value; }
        private MentalStatusExam deletedAggregate() { return deletedAggregate; }
    }
}
