package springboot.application.relationshiptype.dto;


import java.util.UUID;

public record RelationshipTypeResponse(
        UUID id,
        String description
) {
}
