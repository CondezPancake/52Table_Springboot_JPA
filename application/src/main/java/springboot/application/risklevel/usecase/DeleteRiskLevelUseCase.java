package springboot.application.risklevel.usecase;

import java.time.LocalDateTime;

import springboot.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import springboot.domain.risklevel.event.RiskLevelDeletedEvent;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;
import springboot.domain.risklevel.port.repository.RiskLevelRepository;

public class DeleteRiskLevelUseCase {
    private final RiskLevelRepository repository;
    public DeleteRiskLevelUseCase(RiskLevelRepository repository) { this.repository = repository; }

    public RiskLevelDeletedEvent execute(RiskLevelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RiskLevelNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new RiskLevelDeletedEvent(id, LocalDateTime.now());
    }
}
