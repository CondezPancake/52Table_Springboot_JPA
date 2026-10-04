package springboot.application.relationshiptype.usecase;

import springboot.application.relationshiptype.command.UpdateRelationshipTypeCommand;
import springboot.application.relationshiptype.dto.RelationshipTypeResponse;
import springboot.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class UpdateRelationshipTypeUseCase {
    private final RelationshipTypeRepository repository;
    public UpdateRelationshipTypeUseCase(RelationshipTypeRepository repository) { this.repository = repository; }

    public RelationshipTypeResponse execute(UpdateRelationshipTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new RelationshipTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.description());
        var saved = repository.save(aggregate);
        return new RelationshipTypeResponse(
                saved.id().value(),
                saved.description());
    }
}
