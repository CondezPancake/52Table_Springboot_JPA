package springboot.domain.common.exception;

import springboot.domain.encounter.model.valueobject.EncounterId;

public class EncounterNotFoundException extends RuntimeException {
    public EncounterNotFoundException(EncounterId id) {
        super("Encounter not found with id: " + id.value());
    }
}
