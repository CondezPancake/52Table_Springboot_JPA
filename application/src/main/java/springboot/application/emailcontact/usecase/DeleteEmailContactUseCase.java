package springboot.application.emailcontact.usecase;

import java.time.LocalDateTime;

import springboot.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import springboot.domain.emailcontact.event.EmailContactDeletedEvent;
import springboot.domain.emailcontact.model.valueobject.EmailContactId;
import springboot.domain.emailcontact.port.repository.EmailContactRepository;

public class DeleteEmailContactUseCase {
    private final EmailContactRepository repository;
    public DeleteEmailContactUseCase(EmailContactRepository repository) { this.repository = repository; }

    public EmailContactDeletedEvent execute(EmailContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EmailContactNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new EmailContactDeletedEvent(id, LocalDateTime.now());
    }
}
