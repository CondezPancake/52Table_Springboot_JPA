package springboot.domain.common.exception;

import springboot.domain.stateregion.model.valueobject.StateRegionId;

public class StateRegionNotFoundException extends RuntimeException {
    public StateRegionNotFoundException(StateRegionId id) {
        super("StateRegion not found with id: " + id.value());
    }
}
