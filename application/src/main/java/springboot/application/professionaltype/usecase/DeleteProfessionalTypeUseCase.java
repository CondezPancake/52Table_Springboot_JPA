package springboot.application.professionaltype.usecase;

import java.time.LocalDateTime;

import springboot.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import springboot.domain.professionaltype.event.ProfessionalTypeDeletedEvent;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import springboot.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class DeleteProfessionalTypeUseCase {
    private final ProfessionalTypeRepository repository;
    public DeleteProfessionalTypeUseCase(ProfessionalTypeRepository repository) { this.repository = repository; }

    public ProfessionalTypeDeletedEvent execute(ProfessionalTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ProfessionalTypeDeletedEvent(id, LocalDateTime.now());
    }
}
