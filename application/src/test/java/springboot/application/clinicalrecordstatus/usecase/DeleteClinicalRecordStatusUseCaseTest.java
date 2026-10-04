package springboot.application.clinicalrecordstatus.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import springboot.domain.clinicalrecordstatus.event.ClinicalRecordStatusDeletedEvent;
import springboot.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;


class DeleteClinicalRecordStatusUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register(
                "OPEN",
                "Open");
        FakeRepository repository = new FakeRepository(aggregate);
        ClinicalRecordStatusDeletedEvent event = new DeleteClinicalRecordStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClinicalRecordStatusNotFoundApplicationException.class,
                () -> new DeleteClinicalRecordStatusUseCase(repository).execute(ClinicalRecordStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ClinicalRecordStatusRepository {
        private final ClinicalRecordStatus aggregate; private ClinicalRecordStatus deletedAggregate;
        private FakeRepository(ClinicalRecordStatus aggregate) { this.aggregate = aggregate; }
        @Override public ClinicalRecordStatus save(ClinicalRecordStatus value) { return value; }
        @Override public Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ClinicalRecordStatus> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(ClinicalRecordStatus value) { deletedAggregate = value; }
        private ClinicalRecordStatus deletedAggregate() { return deletedAggregate; }
    }
}
