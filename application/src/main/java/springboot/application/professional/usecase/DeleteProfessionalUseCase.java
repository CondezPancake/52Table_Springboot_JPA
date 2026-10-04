package springboot.application.professional.usecase;

import java.time.LocalDateTime;

import springboot.application.professional.exception.ProfessionalNotFoundApplicationException;
import springboot.domain.professional.event.ProfessionalDeletedEvent;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.professional.port.repository.ProfessionalRepository;

public class DeleteProfessionalUseCase {
    private final ProfessionalRepository repository;
    public DeleteProfessionalUseCase(ProfessionalRepository repository) { this.repository = repository; }

    public ProfessionalDeletedEvent execute(ProfessionalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ProfessionalDeletedEvent(id, LocalDateTime.now());
    }
}
