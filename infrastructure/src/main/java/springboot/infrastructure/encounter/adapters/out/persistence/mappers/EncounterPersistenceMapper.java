package springboot.infrastructure.encounter.adapters.out.persistence.mappers;

import springboot.domain.encounter.model.aggregate.Encounter;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;

public class EncounterPersistenceMapper {
    public EncounterJpaEntity toJpa(Encounter domain) {
        if (domain == null) { return null; }
        EncounterJpaEntity jpa = new EncounterJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setClinicalRecordId(domain.clinicalRecordId().value());
        jpa.setProfessionalId(domain.professionalId().value());
        jpa.setEncounterTypeId(domain.encounterTypeId().value());
        jpa.setStartedAt(domain.startedAt());
        jpa.setEndedAt(domain.endedAt());
        jpa.setReasonForVisit(domain.reasonForVisit());
        jpa.setCurrentCondition(domain.currentCondition());
        jpa.setModalityId(domain.modalityId().value());
        jpa.setStatusId(domain.statusId().value());
        jpa.setCreatedBy(domain.createdBy().value());
        jpa.setUpdatedBy(domain.updatedBy().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Encounter toDomain(EncounterJpaEntity jpa) {
        if (jpa == null) { return null; }
        return Encounter.restore(
                new EncounterId(jpa.getId()),
                new ClinicalRecordId(jpa.getClinicalRecordId()),
                new ProfessionalId(jpa.getProfessionalId()),
                new EncounterTypeId(jpa.getEncounterTypeId()),
                jpa.getStartedAt(),
                jpa.getEndedAt(),
                jpa.getReasonForVisit(),
                jpa.getCurrentCondition(),
                new EncounterModalityId(jpa.getModalityId()),
                new EncounterStatusId(jpa.getStatusId()),
                new ProfessionalId(jpa.getCreatedBy()),
                new ProfessionalId(jpa.getUpdatedBy()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
