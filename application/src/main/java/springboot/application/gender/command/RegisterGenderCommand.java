package springboot.application.gender.command;

import java.util.Objects;



public record RegisterGenderCommand(
        String description
) {
    public RegisterGenderCommand {
        Objects.requireNonNull(description, "description must not be null");
    }
}
