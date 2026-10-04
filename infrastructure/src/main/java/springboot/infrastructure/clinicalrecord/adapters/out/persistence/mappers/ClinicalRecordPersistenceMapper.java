package springboot.infrastructure.clinicalrecord.adapters.out.persistence.mappers;

import springboot.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;

public class ClinicalRecordPersistenceMapper {
    public ClinicalRecordJpaEntity toJpa(ClinicalRecord domain) {
        if (domain == null) { return null; }
        ClinicalRecordJpaEntity jpa = new ClinicalRecordJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setPatientId(domain.patientId().value());
        jpa.setCreationDate(domain.creationDate());
        jpa.setRecordNumber(domain.recordNumber());
        jpa.setOpenedAt(domain.openedAt());
        jpa.setClosedAt(domain.closedAt());
        jpa.setStatusId(domain.statusId().value());
        jpa.setCreatedBy(domain.createdBy().value());
        jpa.setCreatedAt(domain.createdAt());
        return jpa;
    }

    public ClinicalRecord toDomain(ClinicalRecordJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ClinicalRecord.restore(
                new ClinicalRecordId(jpa.getId()),
                new PatientId(jpa.getPatientId()),
                jpa.getCreationDate(),
                jpa.getRecordNumber(),
                jpa.getOpenedAt(),
                jpa.getClosedAt(),
                new ClinicalRecordStatusId(jpa.getStatusId()),
                new ProfessionalId(jpa.getCreatedBy()),
                jpa.getCreatedAt());
    }
}
