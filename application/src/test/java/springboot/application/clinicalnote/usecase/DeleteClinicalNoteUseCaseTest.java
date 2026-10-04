package springboot.application.clinicalnote.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import springboot.domain.clinicalnote.event.ClinicalNoteDeletedEvent;
import springboot.domain.clinicalnote.model.aggregate.ClinicalNote;
import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class DeleteClinicalNoteUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ClinicalNote aggregate = ClinicalNote.register(
                EncounterId.generate(),
                ProfessionalId.generate(),
                "Patient report",
                "Clinical observation",
                "Clinical assessment",
                "Follow-up plan",
                "No additional notes",
                java.time.LocalDateTime.of(2026, 1, 10, 9, 0));
        FakeRepository repository = new FakeRepository(aggregate);
        ClinicalNoteDeletedEvent event = new DeleteClinicalNoteUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClinicalNoteNotFoundApplicationException.class,
                () -> new DeleteClinicalNoteUseCase(repository).execute(ClinicalNoteId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ClinicalNoteRepository {
        private final ClinicalNote aggregate; private ClinicalNote deletedAggregate;
        private FakeRepository(ClinicalNote aggregate) { this.aggregate = aggregate; }
        @Override public ClinicalNote save(ClinicalNote value) { return value; }
        @Override public Optional<ClinicalNote> findById(ClinicalNoteId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ClinicalNote> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ClinicalNote value) { deletedAggregate = value; }
        private ClinicalNote deletedAggregate() { return deletedAggregate; }
    }
}
