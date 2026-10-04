package springboot.application.phonecontact.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import springboot.domain.phonecontact.event.PhoneContactDeletedEvent;
import springboot.domain.phonecontact.model.aggregate.PhoneContact;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;
import springboot.domain.phonecontact.port.repository.PhoneContactRepository;
import springboot.domain.contact.model.valueobject.ContactId;

class DeletePhoneContactUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        PhoneContact aggregate = PhoneContact.register(
                ContactId.generate(),
                null,
                "Call after 5 PM");
        FakeRepository repository = new FakeRepository(aggregate);
        PhoneContactDeletedEvent event = new DeletePhoneContactUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(PhoneContactNotFoundApplicationException.class,
                () -> new DeletePhoneContactUseCase(repository).execute(PhoneContactId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements PhoneContactRepository {
        private final PhoneContact aggregate; private PhoneContact deletedAggregate;
        private FakeRepository(PhoneContact aggregate) { this.aggregate = aggregate; }
        @Override public PhoneContact save(PhoneContact value) { return value; }
        @Override public Optional<PhoneContact> findById(PhoneContactId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<PhoneContact> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(PhoneContact value) { deletedAggregate = value; }
        private PhoneContact deletedAggregate() { return deletedAggregate; }
    }
}
