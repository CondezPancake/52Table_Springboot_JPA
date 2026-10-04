package springboot.infrastructure.providermodelai.adapters.in.rest.dtos;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProviderModelAiRequest(
        @NotNull(message = "nameProviderAi is required")
        @Size(max = 100, message = "nameProviderAi must have at most 100 characters")
        String nameProviderAi,

        @NotNull(message = "razonSocial is required")
        @Size(max = 150, message = "razonSocial must have at most 150 characters")
        String razonSocial,

        @NotNull(message = "sitioWeb is required")
        String sitioWeb,

        @NotNull(message = "active is required")
        Boolean active
) {
}
