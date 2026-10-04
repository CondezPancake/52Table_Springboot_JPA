package springboot.application.messagetype.usecase;

import springboot.application.messagetype.command.RegisterMessageTypeCommand;
import springboot.application.messagetype.dto.MessageTypeResponse;
import springboot.domain.messagetype.model.aggregate.MessageType;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;

public class RegisterMessageTypeUseCase {
    private final MessageTypeRepository repository;
    public RegisterMessageTypeUseCase(MessageTypeRepository repository) { this.repository = repository; }

    public MessageTypeResponse execute(RegisterMessageTypeCommand command) {
        MessageType aggregate = MessageType.register(
                command.nameType());
        MessageType saved = repository.save(aggregate);
        return new MessageTypeResponse(
                saved.id().value(),
                saved.nameType(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
