package springboot.application.phonecontact.usecase;

import springboot.application.phonecontact.command.RegisterPhoneContactCommand;
import springboot.application.phonecontact.dto.PhoneContactResponse;
import springboot.domain.phonecontact.model.aggregate.PhoneContact;
import springboot.domain.phonecontact.port.repository.PhoneContactRepository;

public class RegisterPhoneContactUseCase {
    private final PhoneContactRepository repository;
    public RegisterPhoneContactUseCase(PhoneContactRepository repository) { this.repository = repository; }

    public PhoneContactResponse execute(RegisterPhoneContactCommand command) {
        PhoneContact aggregate = PhoneContact.register(
                command.contactId(),
                command.phone(),
                command.notes());
        PhoneContact saved = repository.save(aggregate);
        return new PhoneContactResponse(
                saved.id().value(),
                saved.contactId().value(),
                saved.phone(),
                saved.notes());
    }
}
