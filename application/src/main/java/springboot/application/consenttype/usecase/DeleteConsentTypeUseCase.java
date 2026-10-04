package springboot.application.consenttype.usecase;

import java.time.LocalDateTime;

import springboot.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import springboot.domain.consenttype.event.ConsentTypeDeletedEvent;
import springboot.domain.consenttype.model.valueobject.ConsentTypeId;
import springboot.domain.consenttype.port.repository.ConsentTypeRepository;

public class DeleteConsentTypeUseCase {
    private final ConsentTypeRepository repository;
    public DeleteConsentTypeUseCase(ConsentTypeRepository repository) { this.repository = repository; }

    public ConsentTypeDeletedEvent execute(ConsentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConsentTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ConsentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
