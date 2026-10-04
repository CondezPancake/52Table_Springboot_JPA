package springboot.infrastructure.messagetype.adapters.in.rest.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateMessageTypeRequest(
        @NotNull(message = "nameType is required")
        @Size(max = 50, message = "nameType must have at most 50 characters")
        String nameType
) {
}
