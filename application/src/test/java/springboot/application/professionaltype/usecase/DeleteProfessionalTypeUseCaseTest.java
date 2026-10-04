package springboot.application.professionaltype.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import springboot.domain.professionaltype.event.ProfessionalTypeDeletedEvent;
import springboot.domain.professionaltype.model.aggregate.ProfessionalType;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import springboot.domain.professionaltype.port.repository.ProfessionalTypeRepository;


class DeleteProfessionalTypeUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ProfessionalType aggregate = ProfessionalType.register(
                "Psychologist");
        FakeRepository repository = new FakeRepository(aggregate);
        ProfessionalTypeDeletedEvent event = new DeleteProfessionalTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ProfessionalTypeNotFoundApplicationException.class,
                () -> new DeleteProfessionalTypeUseCase(repository).execute(ProfessionalTypeId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ProfessionalTypeRepository {
        private final ProfessionalType aggregate; private ProfessionalType deletedAggregate;
        private FakeRepository(ProfessionalType aggregate) { this.aggregate = aggregate; }
        @Override public ProfessionalType save(ProfessionalType value) { return value; }
        @Override public Optional<ProfessionalType> findById(ProfessionalTypeId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ProfessionalType> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ProfessionalType value) { deletedAggregate = value; }
        private ProfessionalType deletedAggregate() { return deletedAggregate; }
    }
}
