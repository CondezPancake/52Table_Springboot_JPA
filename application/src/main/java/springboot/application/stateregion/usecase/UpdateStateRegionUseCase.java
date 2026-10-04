package springboot.application.stateregion.usecase;

import springboot.application.stateregion.command.UpdateStateRegionCommand;
import springboot.application.stateregion.dto.StateRegionResponse;
import springboot.application.stateregion.exception.StateRegionNotFoundApplicationException;
import springboot.domain.stateregion.port.repository.StateRegionRepository;

public class UpdateStateRegionUseCase {
    private final StateRegionRepository repository;
    public UpdateStateRegionUseCase(StateRegionRepository repository) { this.repository = repository; }

    public StateRegionResponse execute(UpdateStateRegionCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new StateRegionNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameRegion(),
                command.codeRegion(),
                command.description(),
                command.active(),
                command.countryId());
        var saved = repository.save(aggregate);
        return new StateRegionResponse(
                saved.id().value(),
                saved.nameRegion(),
                saved.codeRegion(),
                saved.description(),
                saved.active(),
                saved.countryId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
