package springboot.application.encountertype.usecase;

import springboot.application.encountertype.dto.EncounterTypeResponse;
import springboot.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;

public class GetEncounterTypeByIdUseCase {
    private final EncounterTypeRepository repository;
    public GetEncounterTypeByIdUseCase(EncounterTypeRepository repository) { this.repository = repository; }

    public EncounterTypeResponse execute(EncounterTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterTypeNotFoundApplicationException(id.value().toString()));
        return new EncounterTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
