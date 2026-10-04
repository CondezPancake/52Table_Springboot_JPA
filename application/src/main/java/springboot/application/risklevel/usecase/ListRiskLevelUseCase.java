package springboot.application.risklevel.usecase;

import java.util.List;

import springboot.application.risklevel.dto.RiskLevelResponse;
import springboot.domain.risklevel.port.repository.RiskLevelRepository;

public class ListRiskLevelUseCase {
    private final RiskLevelRepository repository;
    public ListRiskLevelUseCase(RiskLevelRepository repository) { this.repository = repository; }

    public List<RiskLevelResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new RiskLevelResponse(
                                aggregate.id().value(),
                                aggregate.code(),
                                aggregate.name(),
                                aggregate.active(),
                                aggregate.severity(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
