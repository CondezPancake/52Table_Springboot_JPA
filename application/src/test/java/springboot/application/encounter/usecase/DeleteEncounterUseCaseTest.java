package springboot.application.encounter.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.encounter.exception.EncounterNotFoundApplicationException;
import springboot.domain.encounter.event.EncounterDeletedEvent;
import springboot.domain.encounter.model.aggregate.Encounter;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.encounter.port.repository.EncounterRepository;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class DeleteEncounterUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        Encounter aggregate = Encounter.register(
                ClinicalRecordId.generate(),
                ProfessionalId.generate(),
                EncounterTypeId.generate(),
                java.time.LocalDateTime.of(2026, 1, 10, 8, 0),
                java.time.LocalDateTime.of(2026, 1, 10, 9, 0),
                "Initial consultation",
                "Stable",
                EncounterModalityId.generate(),
                EncounterStatusId.generate(),
                ProfessionalId.generate(),
                ProfessionalId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        EncounterDeletedEvent event = new DeleteEncounterUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(EncounterNotFoundApplicationException.class,
                () -> new DeleteEncounterUseCase(repository).execute(EncounterId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements EncounterRepository {
        private final Encounter aggregate; private Encounter deletedAggregate;
        private FakeRepository(Encounter aggregate) { this.aggregate = aggregate; }
        @Override public Encounter save(Encounter value) { return value; }
        @Override public Optional<Encounter> findById(EncounterId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<Encounter> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(Encounter value) { deletedAggregate = value; }
        private Encounter deletedAggregate() { return deletedAggregate; }
    }
}
