package springboot.application.stateregion.usecase;

import springboot.application.stateregion.dto.StateRegionResponse;
import springboot.application.stateregion.exception.StateRegionNotFoundApplicationException;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.domain.stateregion.port.repository.StateRegionRepository;

public class GetStateRegionByIdUseCase {
    private final StateRegionRepository repository;
    public GetStateRegionByIdUseCase(StateRegionRepository repository) { this.repository = repository; }

    public StateRegionResponse execute(StateRegionId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new StateRegionNotFoundApplicationException(id.value().toString()));
        return new StateRegionResponse(
                aggregate.id().value(),
                aggregate.nameRegion(),
                aggregate.codeRegion(),
                aggregate.description(),
                aggregate.active(),
                aggregate.countryId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
