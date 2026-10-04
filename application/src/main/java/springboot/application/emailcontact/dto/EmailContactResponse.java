package springboot.application.emailcontact.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record EmailContactResponse(
        UUID id,
        UUID contactId,
        String email,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
