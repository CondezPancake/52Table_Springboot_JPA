package springboot.application.stateregion.usecase;

import springboot.application.stateregion.command.RegisterStateRegionCommand;
import springboot.application.stateregion.dto.StateRegionResponse;
import springboot.domain.stateregion.model.aggregate.StateRegion;
import springboot.domain.stateregion.port.repository.StateRegionRepository;

public class RegisterStateRegionUseCase {
    private final StateRegionRepository repository;
    public RegisterStateRegionUseCase(StateRegionRepository repository) { this.repository = repository; }

    public StateRegionResponse execute(RegisterStateRegionCommand command) {
        StateRegion aggregate = StateRegion.register(
                command.nameRegion(),
                command.codeRegion(),
                command.description(),
                command.active(),
                command.countryId());
        StateRegion saved = repository.save(aggregate);
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
