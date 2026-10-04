package springboot.application.study.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.study.exception.StudyNotFoundApplicationException;
import springboot.domain.study.event.StudyDeletedEvent;
import springboot.domain.study.model.aggregate.Study;
import springboot.domain.study.model.valueobject.StudyId;
import springboot.domain.study.port.repository.StudyRepository;


class DeleteStudyUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        Study aggregate = Study.register(
                "Clinical Psychology");
        FakeRepository repository = new FakeRepository(aggregate);
        StudyDeletedEvent event = new DeleteStudyUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(StudyNotFoundApplicationException.class,
                () -> new DeleteStudyUseCase(repository).execute(StudyId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements StudyRepository {
        private final Study aggregate; private Study deletedAggregate;
        private FakeRepository(Study aggregate) { this.aggregate = aggregate; }
        @Override public Study save(Study value) { return value; }
        @Override public Optional<Study> findById(StudyId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<Study> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(Study value) { deletedAggregate = value; }
        private Study deletedAggregate() { return deletedAggregate; }
    }
}
