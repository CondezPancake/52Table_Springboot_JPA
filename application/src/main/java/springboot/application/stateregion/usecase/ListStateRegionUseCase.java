package springboot.application.stateregion.usecase;

import java.util.List;

import springboot.application.stateregion.dto.StateRegionResponse;
import springboot.domain.stateregion.port.repository.StateRegionRepository;

public class ListStateRegionUseCase {
    private final StateRegionRepository repository;
    public ListStateRegionUseCase(StateRegionRepository repository) { this.repository = repository; }

    public List<StateRegionResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new StateRegionResponse(
                                aggregate.id().value(),
                                aggregate.nameRegion(),
                                aggregate.codeRegion(),
                                aggregate.description(),
                                aggregate.active(),
                                aggregate.countryId().value(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
