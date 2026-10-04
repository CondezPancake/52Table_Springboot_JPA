package springboot.application.citymunicipality.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CityMunicipalityResponse(
        UUID id,
        String nameCity,
        String codeCity,
        String description,
        boolean active,
        UUID regionId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
