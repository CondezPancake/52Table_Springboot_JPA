package springboot.application.relationshiptype.usecase;

import springboot.application.relationshiptype.command.RegisterRelationshipTypeCommand;
import springboot.application.relationshiptype.dto.RelationshipTypeResponse;
import springboot.domain.relationshiptype.model.aggregate.RelationshipType;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class RegisterRelationshipTypeUseCase {
    private final RelationshipTypeRepository repository;
    public RegisterRelationshipTypeUseCase(RelationshipTypeRepository repository) { this.repository = repository; }

    public RelationshipTypeResponse execute(RegisterRelationshipTypeCommand command) {
        RelationshipType aggregate = RelationshipType.register(
                command.description());
        RelationshipType saved = repository.save(aggregate);
        return new RelationshipTypeResponse(
                saved.id().value(),
                saved.description());
    }
}
