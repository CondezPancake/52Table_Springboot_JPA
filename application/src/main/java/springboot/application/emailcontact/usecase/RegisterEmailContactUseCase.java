package springboot.application.emailcontact.usecase;

import springboot.application.emailcontact.command.RegisterEmailContactCommand;
import springboot.application.emailcontact.dto.EmailContactResponse;
import springboot.domain.emailcontact.model.aggregate.EmailContact;
import springboot.domain.emailcontact.port.repository.EmailContactRepository;

public class RegisterEmailContactUseCase {
    private final EmailContactRepository repository;
    public RegisterEmailContactUseCase(EmailContactRepository repository) { this.repository = repository; }

    public EmailContactResponse execute(RegisterEmailContactCommand command) {
        EmailContact aggregate = EmailContact.register(
                command.contactId(),
                command.email(),
                command.notes());
        EmailContact saved = repository.save(aggregate);
        return new EmailContactResponse(
                saved.id().value(),
                saved.contactId().value(),
                saved.email(),
                saved.notes(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
