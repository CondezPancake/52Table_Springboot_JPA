package springboot.application.patientcontact.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import springboot.domain.patientcontact.event.PatientContactDeletedEvent;
import springboot.domain.patientcontact.model.aggregate.PatientContact;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;
import springboot.domain.patientcontact.port.repository.PatientContactRepository;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;

class DeletePatientContactUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        PatientContact aggregate = PatientContact.register(
                ContactId.generate(),
                PatientId.generate(),
                true,
                false,
                RelationshipTypeId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        PatientContactDeletedEvent event = new DeletePatientContactUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(PatientContactNotFoundApplicationException.class,
                () -> new DeletePatientContactUseCase(repository).execute(PatientContactId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements PatientContactRepository {
        private final PatientContact aggregate; private PatientContact deletedAggregate;
        private FakeRepository(PatientContact aggregate) { this.aggregate = aggregate; }
        @Override public PatientContact save(PatientContact value) { return value; }
        @Override public Optional<PatientContact> findById(PatientContactId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<PatientContact> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(PatientContact value) { deletedAggregate = value; }
        private PatientContact deletedAggregate() { return deletedAggregate; }
    }
}
