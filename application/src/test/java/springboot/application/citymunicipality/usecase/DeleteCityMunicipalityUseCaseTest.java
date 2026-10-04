package springboot.application.citymunicipality.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import springboot.domain.citymunicipality.event.CityMunicipalityDeletedEvent;
import springboot.domain.citymunicipality.model.aggregate.CityMunicipality;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import springboot.domain.stateregion.model.valueobject.StateRegionId;

class DeleteCityMunicipalityUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        CityMunicipality aggregate = CityMunicipality.register(
                "Bogota",
                "BOG",
                "Capital district",
                true,
                StateRegionId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        CityMunicipalityDeletedEvent event = new DeleteCityMunicipalityUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CityMunicipalityNotFoundApplicationException.class,
                () -> new DeleteCityMunicipalityUseCase(repository).execute(CityMunicipalityId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements CityMunicipalityRepository {
        private final CityMunicipality aggregate; private CityMunicipality deletedAggregate;
        private FakeRepository(CityMunicipality aggregate) { this.aggregate = aggregate; }
        @Override public CityMunicipality save(CityMunicipality value) { return value; }
        @Override public Optional<CityMunicipality> findById(CityMunicipalityId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<CityMunicipality> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(CityMunicipality value) { deletedAggregate = value; }
        private CityMunicipality deletedAggregate() { return deletedAggregate; }
    }
}
