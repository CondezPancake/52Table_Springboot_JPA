package springboot.application.sendertype.usecase;

import springboot.application.sendertype.command.UpdateSenderTypeCommand;
import springboot.application.sendertype.dto.SenderTypeResponse;
import springboot.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;

public class UpdateSenderTypeUseCase {
    private final SenderTypeRepository repository;
    public UpdateSenderTypeUseCase(SenderTypeRepository repository) { this.repository = repository; }

    public SenderTypeResponse execute(UpdateSenderTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SenderTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameType());
        var saved = repository.save(aggregate);
        return new SenderTypeResponse(
                saved.id().value(),
                saved.nameType(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
