package springboot.application.diagnosticsystem.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import springboot.domain.diagnosticsystem.event.DiagnosticSystemDeletedEvent;
import springboot.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import springboot.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;


class DeleteDiagnosticSystemUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        DiagnosticSystem aggregate = DiagnosticSystem.register(
                "DSM",
                "Diagnostic and Statistical Manual",
                true,
                "5-TR");
        FakeRepository repository = new FakeRepository(aggregate);
        DiagnosticSystemDeletedEvent event = new DeleteDiagnosticSystemUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DiagnosticSystemNotFoundApplicationException.class,
                () -> new DeleteDiagnosticSystemUseCase(repository).execute(DiagnosticSystemId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements DiagnosticSystemRepository {
        private final DiagnosticSystem aggregate; private DiagnosticSystem deletedAggregate;
        private FakeRepository(DiagnosticSystem aggregate) { this.aggregate = aggregate; }
        @Override public DiagnosticSystem save(DiagnosticSystem value) { return value; }
        @Override public Optional<DiagnosticSystem> findById(DiagnosticSystemId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<DiagnosticSystem> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
        @Override public boolean existsByCode(String code) { return false; }
        @Override public void delete(DiagnosticSystem value) { deletedAggregate = value; }
        private DiagnosticSystem deletedAggregate() { return deletedAggregate; }
    }
}
