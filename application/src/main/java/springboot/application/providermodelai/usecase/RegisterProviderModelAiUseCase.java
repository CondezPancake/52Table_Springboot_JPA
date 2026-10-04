package springboot.application.providermodelai.usecase;

import springboot.application.providermodelai.command.RegisterProviderModelAiCommand;
import springboot.application.providermodelai.dto.ProviderModelAiResponse;
import springboot.domain.providermodelai.model.aggregate.ProviderModelAi;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class RegisterProviderModelAiUseCase {
    private final ProviderModelAiRepository repository;
    public RegisterProviderModelAiUseCase(ProviderModelAiRepository repository) { this.repository = repository; }

    public ProviderModelAiResponse execute(RegisterProviderModelAiCommand command) {
        ProviderModelAi aggregate = ProviderModelAi.register(
                command.nameProviderAi(),
                command.razonSocial(),
                command.sitioWeb(),
                command.active());
        ProviderModelAi saved = repository.save(aggregate);
        return new ProviderModelAiResponse(
                saved.id().value(),
                saved.nameProviderAi(),
                saved.razonSocial(),
                saved.sitioWeb(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
