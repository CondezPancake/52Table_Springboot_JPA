package springboot.application.treatmentgoalstatus.usecase;

import java.util.List;

import springboot.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import springboot.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class ListTreatmentGoalStatusUseCase {
    private final TreatmentGoalStatusRepository repository;
    public ListTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) { this.repository = repository; }

    public List<TreatmentGoalStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new TreatmentGoalStatusResponse(
                                aggregate.id().value(),
                                aggregate.code(),
                                aggregate.name(),
                                aggregate.active(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
