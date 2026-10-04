package springboot.application.emailcontact.usecase;

import springboot.application.emailcontact.command.UpdateEmailContactCommand;
import springboot.application.emailcontact.dto.EmailContactResponse;
import springboot.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import springboot.domain.emailcontact.port.repository.EmailContactRepository;

public class UpdateEmailContactUseCase {
    private final EmailContactRepository repository;
    public UpdateEmailContactUseCase(EmailContactRepository repository) { this.repository = repository; }

    public EmailContactResponse execute(UpdateEmailContactCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EmailContactNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.contactId(),
                command.email(),
                command.notes());
        var saved = repository.save(aggregate);
        return new EmailContactResponse(
                saved.id().value(),
                saved.contactId().value(),
                saved.email(),
                saved.notes(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
