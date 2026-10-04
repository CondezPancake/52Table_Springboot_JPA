package springboot.application.risklevel.usecase;

import springboot.application.risklevel.command.UpdateRiskLevelCommand;
import springboot.application.risklevel.dto.RiskLevelResponse;
import springboot.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import springboot.domain.risklevel.port.repository.RiskLevelRepository;

public class UpdateRiskLevelUseCase {
    private final RiskLevelRepository repository;
    public UpdateRiskLevelUseCase(RiskLevelRepository repository) { this.repository = repository; }

    public RiskLevelResponse execute(UpdateRiskLevelCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new RiskLevelNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active(),
                command.severity());
        var saved = repository.save(aggregate);
        return new RiskLevelResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.severity(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
