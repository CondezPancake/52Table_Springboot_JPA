package springboot.application.treatmentstatus.usecase;

import springboot.application.treatmentstatus.dto.TreatmentStatusResponse;
import springboot.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import springboot.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class GetTreatmentStatusByIdUseCase {
    private final TreatmentStatusRepository repository;
    public GetTreatmentStatusByIdUseCase(TreatmentStatusRepository repository) { this.repository = repository; }

    public TreatmentStatusResponse execute(TreatmentStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentStatusNotFoundApplicationException(id.value().toString()));
        return new TreatmentStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
