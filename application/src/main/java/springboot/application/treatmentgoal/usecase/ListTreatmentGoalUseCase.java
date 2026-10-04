package springboot.application.treatmentgoal.usecase;

import java.util.List;

import springboot.application.treatmentgoal.dto.TreatmentGoalResponse;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class ListTreatmentGoalUseCase {
    private final TreatmentGoalRepository repository;
    public ListTreatmentGoalUseCase(TreatmentGoalRepository repository) { this.repository = repository; }

    public List<TreatmentGoalResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new TreatmentGoalResponse(
                                aggregate.id().value(),
                                aggregate.treatmentPlanId().value(),
                                aggregate.description(),
                                aggregate.targetDate(),
                                aggregate.completedAt(),
                                aggregate.notes(),
                                aggregate.treatmentGoalStatusId().value(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
