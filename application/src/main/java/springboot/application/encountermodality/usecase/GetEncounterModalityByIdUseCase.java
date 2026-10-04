package springboot.application.encountermodality.usecase;

import springboot.application.encountermodality.dto.EncounterModalityResponse;
import springboot.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;

public class GetEncounterModalityByIdUseCase {
    private final EncounterModalityRepository repository;
    public GetEncounterModalityByIdUseCase(EncounterModalityRepository repository) { this.repository = repository; }

    public EncounterModalityResponse execute(EncounterModalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterModalityNotFoundApplicationException(id.value().toString()));
        return new EncounterModalityResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
