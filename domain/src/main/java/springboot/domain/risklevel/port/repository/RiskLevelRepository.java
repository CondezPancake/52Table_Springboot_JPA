package springboot.domain.risklevel.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.risklevel.model.aggregate.RiskLevel;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;

public interface RiskLevelRepository {
    RiskLevel save(RiskLevel aggregate);
    Optional<RiskLevel> findById(RiskLevelId id);
    List<RiskLevel> findAll();
    boolean existsByCode(String code);
    void delete(RiskLevel aggregate);
}
