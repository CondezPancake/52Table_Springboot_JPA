package springboot.application.clinicalrecord.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ClinicalRecordResponse(
        UUID id,
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId,
        UUID createdBy,
        LocalDateTime createdAt
) {
}
