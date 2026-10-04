package springboot.application.airunstatus.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import springboot.domain.airunstatus.event.AiRunStatusDeletedEvent;
import springboot.domain.airunstatus.model.aggregate.AiRunStatus;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;


class DeleteAiRunStatusUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        AiRunStatus aggregate = AiRunStatus.register(
                "Completed");
        FakeRepository repository = new FakeRepository(aggregate);
        AiRunStatusDeletedEvent event = new DeleteAiRunStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AiRunStatusNotFoundApplicationException.class,
                () -> new DeleteAiRunStatusUseCase(repository).execute(AiRunStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements AiRunStatusRepository {
        private final AiRunStatus aggregate; private AiRunStatus deletedAggregate;
        private FakeRepository(AiRunStatus aggregate) { this.aggregate = aggregate; }
        @Override public AiRunStatus save(AiRunStatus value) { return value; }
        @Override public Optional<AiRunStatus> findById(AiRunStatusId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<AiRunStatus> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(AiRunStatus value) { deletedAggregate = value; }
        private AiRunStatus deletedAggregate() { return deletedAggregate; }
    }
}
