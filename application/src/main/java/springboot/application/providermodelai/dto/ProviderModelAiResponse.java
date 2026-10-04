package springboot.application.providermodelai.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ProviderModelAiResponse(
        UUID id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
