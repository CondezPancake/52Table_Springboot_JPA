package springboot.application.relationshiptype.usecase;

import java.time.LocalDateTime;

import springboot.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import springboot.domain.relationshiptype.event.RelationshipTypeDeletedEvent;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class DeleteRelationshipTypeUseCase {
    private final RelationshipTypeRepository repository;
    public DeleteRelationshipTypeUseCase(RelationshipTypeRepository repository) { this.repository = repository; }

    public RelationshipTypeDeletedEvent execute(RelationshipTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RelationshipTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new RelationshipTypeDeletedEvent(id, LocalDateTime.now());
    }
}
