package springboot.application.patient.dto;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

public record PatientResponse(
        UUID id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        UUID biologicalSexId,
        UUID genderIdentityId,
        String email,
        String phone,
        String address,
        boolean active,
        UUID createdBy,
        UUID updatedBy,
        UUID cityId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
