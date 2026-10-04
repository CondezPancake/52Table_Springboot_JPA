package springboot.application.country.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CountryResponse(
        UUID id,
        String nameCountry,
        String codeCountry,
        String description,
        boolean active,
        String telephonePrefix,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
