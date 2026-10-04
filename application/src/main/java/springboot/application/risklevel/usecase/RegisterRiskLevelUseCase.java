package springboot.application.risklevel.usecase;

import springboot.application.risklevel.command.RegisterRiskLevelCommand;
import springboot.application.risklevel.dto.RiskLevelResponse;
import springboot.domain.risklevel.model.aggregate.RiskLevel;
import springboot.domain.risklevel.port.repository.RiskLevelRepository;

public class RegisterRiskLevelUseCase {
    private final RiskLevelRepository repository;
    public RegisterRiskLevelUseCase(RiskLevelRepository repository) { this.repository = repository; }

    public RiskLevelResponse execute(RegisterRiskLevelCommand command) {
        RiskLevel aggregate = RiskLevel.register(
                command.code(),
                command.name(),
                command.active(),
                command.severity());
        RiskLevel saved = repository.save(aggregate);
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
