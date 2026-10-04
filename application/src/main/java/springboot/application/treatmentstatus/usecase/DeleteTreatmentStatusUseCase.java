package springboot.application.treatmentstatus.usecase;

import java.time.LocalDateTime;

import springboot.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import springboot.domain.treatmentstatus.event.TreatmentStatusDeletedEvent;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import springboot.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class DeleteTreatmentStatusUseCase {
    private final TreatmentStatusRepository repository;
    public DeleteTreatmentStatusUseCase(TreatmentStatusRepository repository) { this.repository = repository; }

    public TreatmentStatusDeletedEvent execute(TreatmentStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new TreatmentStatusDeletedEvent(id, LocalDateTime.now());
    }
}
