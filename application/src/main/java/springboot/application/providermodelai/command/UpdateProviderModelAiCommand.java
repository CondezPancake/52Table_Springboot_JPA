package springboot.application.providermodelai.command;

import java.util.Objects;


import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;


public record UpdateProviderModelAiCommand(
        ProviderModelAiId id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        boolean active
) {
    public UpdateProviderModelAiCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
        Objects.requireNonNull(razonSocial, "razonSocial must not be null");
        Objects.requireNonNull(sitioWeb, "sitioWeb must not be null");
    }
}
