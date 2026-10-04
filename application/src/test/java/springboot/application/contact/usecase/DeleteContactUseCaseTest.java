package springboot.application.contact.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.contact.exception.ContactNotFoundApplicationException;
import springboot.domain.contact.event.ContactDeletedEvent;
import springboot.domain.contact.model.aggregate.Contact;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.contact.port.repository.ContactRepository;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class DeleteContactUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        Contact aggregate = Contact.register(
                "Emergency Contact",
                "contact@example.com",
                "Primary contact",
                CityMunicipalityId.generate(),
                ProfessionalId.generate(),
                null);
        FakeRepository repository = new FakeRepository(aggregate);
        ContactDeletedEvent event = new DeleteContactUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ContactNotFoundApplicationException.class,
                () -> new DeleteContactUseCase(repository).execute(ContactId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ContactRepository {
        private final Contact aggregate; private Contact deletedAggregate;
        private FakeRepository(Contact aggregate) { this.aggregate = aggregate; }
        @Override public Contact save(Contact value) { return value; }
        @Override public Optional<Contact> findById(ContactId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<Contact> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(Contact value) { deletedAggregate = value; }
        private Contact deletedAggregate() { return deletedAggregate; }
    }
}
