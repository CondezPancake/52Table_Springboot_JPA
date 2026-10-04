package springboot.domain.common.exception;

import springboot.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskLevelNotFoundException extends RuntimeException {
    public RiskLevelNotFoundException(RiskLevelId id) {
        super("RiskLevel not found with id: " + id.value());
    }
}
