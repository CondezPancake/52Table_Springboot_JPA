package springboot.domain.stateregion.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.stateregion.model.aggregate.StateRegion;
import springboot.domain.stateregion.model.valueobject.StateRegionId;

public interface StateRegionRepository {
    StateRegion save(StateRegion aggregate);
    Optional<StateRegion> findById(StateRegionId id);
    List<StateRegion> findAll();
    boolean existsByCode(String code);
    void delete(StateRegion aggregate);
}
