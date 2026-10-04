package springboot.application.providermodelai.command;

import java.util.Objects;




public record RegisterProviderModelAiCommand(
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        boolean active
) {
    public RegisterProviderModelAiCommand {
        Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
        Objects.requireNonNull(razonSocial, "razonSocial must not be null");
        Objects.requireNonNull(sitioWeb, "sitioWeb must not be null");
    }
}
