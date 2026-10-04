package springboot.application.messagetype.usecase;

import springboot.application.messagetype.command.UpdateMessageTypeCommand;
import springboot.application.messagetype.dto.MessageTypeResponse;
import springboot.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;

public class UpdateMessageTypeUseCase {
    private final MessageTypeRepository repository;
    public UpdateMessageTypeUseCase(MessageTypeRepository repository) { this.repository = repository; }

    public MessageTypeResponse execute(UpdateMessageTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MessageTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameType());
        var saved = repository.save(aggregate);
        return new MessageTypeResponse(
                saved.id().value(),
                saved.nameType(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
