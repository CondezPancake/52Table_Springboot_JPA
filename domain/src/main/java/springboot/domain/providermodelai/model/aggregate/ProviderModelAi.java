package springboot.domain.providermodelai.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.providermodelai.event.ProviderModelAiRegisteredEvent;
import springboot.domain.providermodelai.event.ProviderModelAiUpdatedEvent;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;


public class ProviderModelAi extends AggregateRoot {
    private final ProviderModelAiId id;
    private String nameProviderAi;
    private String razonSocial;
    private String sitioWeb;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProviderModelAi(
            ProviderModelAiId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameProviderAi = Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
        this.razonSocial = Objects.requireNonNull(razonSocial, "razonSocial must not be null");
        this.sitioWeb = Objects.requireNonNull(sitioWeb, "sitioWeb must not be null");
        this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ProviderModelAi register(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active) {
        ProviderModelAiId id = ProviderModelAiId.generate();
        LocalDateTime now = LocalDateTime.now();
        ProviderModelAi aggregate = new ProviderModelAi(
                id,
                nameProviderAi,
                razonSocial,
                sitioWeb,
                active,
                now,
                now);
        aggregate.recordEvent(new ProviderModelAiRegisteredEvent(id, now));
        return aggregate;
    }

    public static ProviderModelAi restore(
            ProviderModelAiId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProviderModelAi(
                id,
                nameProviderAi,
                razonSocial,
                sitioWeb,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active) {
        this.nameProviderAi = Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
        this.razonSocial = Objects.requireNonNull(razonSocial, "razonSocial must not be null");
        this.sitioWeb = Objects.requireNonNull(sitioWeb, "sitioWeb must not be null");
        this.active = active;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ProviderModelAiUpdatedEvent(
                        this.id,
                        this.nameProviderAi,
                        this.razonSocial,
                        this.sitioWeb,
                        this.active,
                        this.updatedAt));
    }

    public ProviderModelAiId id() {
        return id;
    }

    public String nameProviderAi() {
        return nameProviderAi;
    }

    public String razonSocial() {
        return razonSocial;
    }

    public String sitioWeb() {
        return sitioWeb;
    }

    public boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
