package springboot.application.clinicalrecordstatus.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ClinicalRecordStatusResponse(
        UUID id,
        String code,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
