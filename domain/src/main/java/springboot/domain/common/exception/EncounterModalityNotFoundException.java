package springboot.domain.common.exception;

import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;

public class EncounterModalityNotFoundException extends RuntimeException {
    public EncounterModalityNotFoundException(EncounterModalityId id) {
        super("EncounterModality not found with id: " + id.value());
    }
}
