package springboot.application.phonecontact.usecase;

import springboot.application.phonecontact.command.UpdatePhoneContactCommand;
import springboot.application.phonecontact.dto.PhoneContactResponse;
import springboot.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import springboot.domain.phonecontact.port.repository.PhoneContactRepository;

public class UpdatePhoneContactUseCase {
    private final PhoneContactRepository repository;
    public UpdatePhoneContactUseCase(PhoneContactRepository repository) { this.repository = repository; }

    public PhoneContactResponse execute(UpdatePhoneContactCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PhoneContactNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.contactId(),
                command.phone(),
                command.notes());
        var saved = repository.save(aggregate);
        return new PhoneContactResponse(
                saved.id().value(),
                saved.contactId().value(),
                saved.phone(),
                saved.notes());
    }
}
