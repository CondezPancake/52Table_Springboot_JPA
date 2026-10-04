package springboot.infrastructure.relationshiptype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRelationshipTypeRequest(
        @NotNull(message = "description is required")
        @Size(max = 50, message = "description must have at most 50 characters")
        String description
) {
}
