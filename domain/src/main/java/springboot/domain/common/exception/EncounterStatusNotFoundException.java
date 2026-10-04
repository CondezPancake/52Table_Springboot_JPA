package springboot.domain.common.exception;

import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;

public class EncounterStatusNotFoundException extends RuntimeException {
    public EncounterStatusNotFoundException(EncounterStatusId id) {
        super("EncounterStatus not found with id: " + id.value());
    }
}
