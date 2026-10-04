package springboot.application.relationshiptype.command;

import java.util.Objects;



public record RegisterRelationshipTypeCommand(
        String description
) {
    public RegisterRelationshipTypeCommand {
        Objects.requireNonNull(description, "description must not be null");
    }
}
