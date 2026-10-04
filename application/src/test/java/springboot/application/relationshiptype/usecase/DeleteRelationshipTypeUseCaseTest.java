package springboot.application.relationshiptype.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import springboot.domain.relationshiptype.event.RelationshipTypeDeletedEvent;
import springboot.domain.relationshiptype.model.aggregate.RelationshipType;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;


class DeleteRelationshipTypeUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        RelationshipType aggregate = RelationshipType.register(
                "Parent");
        FakeRepository repository = new FakeRepository(aggregate);
        RelationshipTypeDeletedEvent event = new DeleteRelationshipTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(RelationshipTypeNotFoundApplicationException.class,
                () -> new DeleteRelationshipTypeUseCase(repository).execute(RelationshipTypeId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements RelationshipTypeRepository {
        private final RelationshipType aggregate; private RelationshipType deletedAggregate;
        private FakeRepository(RelationshipType aggregate) { this.aggregate = aggregate; }
        @Override public RelationshipType save(RelationshipType value) { return value; }
        @Override public Optional<RelationshipType> findById(RelationshipTypeId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<RelationshipType> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(RelationshipType value) { deletedAggregate = value; }
        private RelationshipType deletedAggregate() { return deletedAggregate; }
    }
}
