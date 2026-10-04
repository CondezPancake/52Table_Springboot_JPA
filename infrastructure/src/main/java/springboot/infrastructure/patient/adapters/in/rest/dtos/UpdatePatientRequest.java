package springboot.infrastructure.patient.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePatientRequest(
        @NotNull(message = "documentTypeId is required")
        UUID documentTypeId,

        @NotNull(message = "documentNumber is required")
        @Size(max = 30, message = "documentNumber must have at most 30 characters")
        String documentNumber,

        @NotNull(message = "firstName is required")
        @Size(max = 50, message = "firstName must have at most 50 characters")
        String firstName,

        @Size(max = 50, message = "middleName must have at most 50 characters")
        String middleName,

        @NotNull(message = "lastName is required")
        @Size(max = 50, message = "lastName must have at most 50 characters")
        String lastName,

        @Size(max = 50, message = "secondLastName must have at most 50 characters")
        String secondLastName,

        @NotNull(message = "birthDate is required")
        LocalDate birthDate,

        @NotNull(message = "biologicalSexId is required")
        UUID biologicalSexId,

        @NotNull(message = "genderIdentityId is required")
        UUID genderIdentityId,

        @NotNull(message = "email is required")
        @Size(max = 150, message = "email must have at most 150 characters")
        String email,

        @NotNull(message = "phone is required")
        @Size(max = 30, message = "phone must have at most 30 characters")
        String phone,

        @NotNull(message = "address is required")
        @Size(max = 250, message = "address must have at most 250 characters")
        String address,

        @NotNull(message = "active is required")
        Boolean active,

        UUID createdBy,

        UUID updatedBy,

        @NotNull(message = "cityId is required")
        UUID cityId
) {
}
