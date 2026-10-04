package springboot.application.contact.usecase;

import java.time.LocalDateTime;

import springboot.application.contact.exception.ContactNotFoundApplicationException;
import springboot.domain.contact.event.ContactDeletedEvent;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.contact.port.repository.ContactRepository;

public class DeleteContactUseCase {
    private final ContactRepository repository;
    public DeleteContactUseCase(ContactRepository repository) { this.repository = repository; }

    public ContactDeletedEvent execute(ContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ContactDeletedEvent(id, LocalDateTime.now());
    }
}
