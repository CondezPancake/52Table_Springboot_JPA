package springboot.application.stateregion.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record StateRegionResponse(
        UUID id,
        String nameRegion,
        String codeRegion,
        String description,
        boolean active,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
