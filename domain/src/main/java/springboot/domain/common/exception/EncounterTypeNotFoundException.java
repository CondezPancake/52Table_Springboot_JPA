package springboot.domain.common.exception;

import springboot.domain.encountertype.model.valueobject.EncounterTypeId;

public class EncounterTypeNotFoundException extends RuntimeException {
    public EncounterTypeNotFoundException(EncounterTypeId id) {
        super("EncounterType not found with id: " + id.value());
    }
}
