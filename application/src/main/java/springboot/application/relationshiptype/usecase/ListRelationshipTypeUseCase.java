package springboot.application.relationshiptype.usecase;

import java.util.List;

import springboot.application.relationshiptype.dto.RelationshipTypeResponse;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class ListRelationshipTypeUseCase {
    private final RelationshipTypeRepository repository;
    public ListRelationshipTypeUseCase(RelationshipTypeRepository repository) { this.repository = repository; }

    public List<RelationshipTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new RelationshipTypeResponse(
                                aggregate.id().value(),
                                aggregate.description()))
                .toList();
    }
}
