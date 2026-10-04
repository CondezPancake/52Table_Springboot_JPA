package springboot.application.providermodelai.usecase;

import springboot.application.providermodelai.command.UpdateProviderModelAiCommand;
import springboot.application.providermodelai.dto.ProviderModelAiResponse;
import springboot.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class UpdateProviderModelAiUseCase {
    private final ProviderModelAiRepository repository;
    public UpdateProviderModelAiUseCase(ProviderModelAiRepository repository) { this.repository = repository; }

    public ProviderModelAiResponse execute(UpdateProviderModelAiCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProviderModelAiNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameProviderAi(),
                command.razonSocial(),
                command.sitioWeb(),
                command.active());
        var saved = repository.save(aggregate);
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
