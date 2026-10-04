package springboot.application.phonecontact.usecase;

import java.time.LocalDateTime;

import springboot.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import springboot.domain.phonecontact.event.PhoneContactDeletedEvent;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;
import springboot.domain.phonecontact.port.repository.PhoneContactRepository;

public class DeletePhoneContactUseCase {
    private final PhoneContactRepository repository;
    public DeletePhoneContactUseCase(PhoneContactRepository repository) { this.repository = repository; }

    public PhoneContactDeletedEvent execute(PhoneContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PhoneContactNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new PhoneContactDeletedEvent(id, LocalDateTime.now());
    }
}
