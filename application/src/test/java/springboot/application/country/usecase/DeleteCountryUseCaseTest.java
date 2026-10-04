package springboot.application.country.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.country.exception.CountryNotFoundApplicationException;
import springboot.domain.country.event.CountryDeletedEvent;
import springboot.domain.country.model.aggregate.Country;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.country.port.repository.CountryRepository;


class DeleteCountryUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        Country aggregate = Country.register(
                "Colombia",
                "CO",
                "Republic of Colombia",
                true,
                "+57");
        FakeRepository repository = new FakeRepository(aggregate);
        CountryDeletedEvent event = new DeleteCountryUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CountryNotFoundApplicationException.class,
                () -> new DeleteCountryUseCase(repository).execute(CountryId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements CountryRepository {
        private final Country aggregate; private Country deletedAggregate;
        private FakeRepository(Country aggregate) { this.aggregate = aggregate; }
        @Override public Country save(Country value) { return value; }
        @Override public Optional<Country> findById(CountryId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<Country> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(Country value) { deletedAggregate = value; }
        private Country deletedAggregate() { return deletedAggregate; }
    }
}
