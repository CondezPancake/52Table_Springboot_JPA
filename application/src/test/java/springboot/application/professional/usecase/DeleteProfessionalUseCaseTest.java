package springboot.application.professional.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.professional.exception.ProfessionalNotFoundApplicationException;
import springboot.domain.professional.event.ProfessionalDeletedEvent;
import springboot.domain.professional.model.aggregate.Professional;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.professional.port.repository.ProfessionalRepository;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

class DeleteProfessionalUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        Professional aggregate = Professional.register(
                DocumentTypeId.generate(),
                "123456789",
                "Ana",
                "Ramirez",
                ProfessionalTypeId.generate(),
                "PSY-12345",
                true,
                CityMunicipalityId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        ProfessionalDeletedEvent event = new DeleteProfessionalUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ProfessionalNotFoundApplicationException.class,
                () -> new DeleteProfessionalUseCase(repository).execute(ProfessionalId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ProfessionalRepository {
        private final Professional aggregate; private Professional deletedAggregate;
        private FakeRepository(Professional aggregate) { this.aggregate = aggregate; }
        @Override public Professional save(Professional value) { return value; }
        @Override public Optional<Professional> findById(ProfessionalId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<Professional> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(Professional value) { deletedAggregate = value; }
        private Professional deletedAggregate() { return deletedAggregate; }
    }
}
