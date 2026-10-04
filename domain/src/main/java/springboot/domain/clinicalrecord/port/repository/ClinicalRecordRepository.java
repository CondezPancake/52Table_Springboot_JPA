package springboot.domain.clinicalrecord.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public interface ClinicalRecordRepository {
    ClinicalRecord save(ClinicalRecord aggregate);
    Optional<ClinicalRecord> findById(ClinicalRecordId id);
    List<ClinicalRecord> findAll();

    void delete(ClinicalRecord aggregate);
}
