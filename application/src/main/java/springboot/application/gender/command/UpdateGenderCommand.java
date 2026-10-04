package springboot.application.gender.command;

import java.util.Objects;

import springboot.domain.gender.model.valueobject.GenderId;


public record UpdateGenderCommand(
        GenderId id,
        String description
) {
    public UpdateGenderCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(description, "description must not be null");
    }
}
