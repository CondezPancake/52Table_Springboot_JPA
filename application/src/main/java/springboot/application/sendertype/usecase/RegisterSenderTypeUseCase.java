package springboot.application.sendertype.usecase;

import springboot.application.sendertype.command.RegisterSenderTypeCommand;
import springboot.application.sendertype.dto.SenderTypeResponse;
import springboot.domain.sendertype.model.aggregate.SenderType;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;

public class RegisterSenderTypeUseCase {
    private final SenderTypeRepository repository;
    public RegisterSenderTypeUseCase(SenderTypeRepository repository) { this.repository = repository; }

    public SenderTypeResponse execute(RegisterSenderTypeCommand command) {
        SenderType aggregate = SenderType.register(
                command.nameType());
        SenderType saved = repository.save(aggregate);
        return new SenderTypeResponse(
                saved.id().value(),
                saved.nameType(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
