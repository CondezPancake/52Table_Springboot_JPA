package springboot.application.diagnosticsystem.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record DiagnosticSystemResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        String version,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
