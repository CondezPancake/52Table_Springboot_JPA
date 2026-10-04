package springboot.domain.clinicalrecordstatus.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public interface ClinicalRecordStatusRepository {
    ClinicalRecordStatus save(ClinicalRecordStatus aggregate);
    Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id);
    List<ClinicalRecordStatus> findAll();
    boolean existsByCode(String code);
    void delete(ClinicalRecordStatus aggregate);
}
