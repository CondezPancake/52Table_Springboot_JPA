package springboot.domain.treatmentstatus.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public interface TreatmentStatusRepository {
    TreatmentStatus save(TreatmentStatus aggregate);
    Optional<TreatmentStatus> findById(TreatmentStatusId id);
    List<TreatmentStatus> findAll();
    boolean existsByCode(String code);
    void delete(TreatmentStatus aggregate);
}
