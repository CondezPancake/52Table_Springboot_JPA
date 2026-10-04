package springboot.infrastructure.airunstatus.adapters.in.rest.dtos;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAiRunStatusRequest(
        @NotNull(message = "nameStatus is required")
        @Size(max = 50, message = "nameStatus must have at most 50 characters")
        String nameStatus
) {
}
