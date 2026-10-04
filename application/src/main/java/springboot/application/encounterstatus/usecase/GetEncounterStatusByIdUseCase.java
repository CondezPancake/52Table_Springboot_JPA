package springboot.application.encounterstatus.usecase;

import springboot.application.encounterstatus.dto.EncounterStatusResponse;
import springboot.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class GetEncounterStatusByIdUseCase {
    private final EncounterStatusRepository repository;
    public GetEncounterStatusByIdUseCase(EncounterStatusRepository repository) { this.repository = repository; }

    public EncounterStatusResponse execute(EncounterStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterStatusNotFoundApplicationException(id.value().toString()));
        return new EncounterStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
