package springboot.application.relationshiptype.usecase;

import springboot.application.relationshiptype.dto.RelationshipTypeResponse;
import springboot.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class GetRelationshipTypeByIdUseCase {
    private final RelationshipTypeRepository repository;
    public GetRelationshipTypeByIdUseCase(RelationshipTypeRepository repository) { this.repository = repository; }

    public RelationshipTypeResponse execute(RelationshipTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RelationshipTypeNotFoundApplicationException(id.value().toString()));
        return new RelationshipTypeResponse(
                aggregate.id().value(),
                aggregate.description());
    }
}
