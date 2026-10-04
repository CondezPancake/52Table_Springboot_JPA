CREATE TABLE encounters (
    id CHAR(36) NOT NULL,
    clinical_record_id CHAR(36) NOT NULL,
    professional_id CHAR(36) NOT NULL,
    encounter_type_id CHAR(36) NOT NULL,
    started_at TIMESTAMP NOT NULL,
    ended_at TIMESTAMP NOT NULL,
    reason_for_visit TEXT NOT NULL,
    current_condition TEXT NOT NULL,
    modality_id CHAR(36) NOT NULL,
    status_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by CHAR(36) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    updated_by CHAR(36) NOT NULL,
    CONSTRAINT pk_encounters PRIMARY KEY (id),
    CONSTRAINT fk_encounters_clinical_record
        FOREIGN KEY (clinical_record_id) REFERENCES clinical_records (id),
    CONSTRAINT fk_encounters_professional
        FOREIGN KEY (professional_id) REFERENCES professionals (id),
    CONSTRAINT fk_encounters_type
        FOREIGN KEY (encounter_type_id) REFERENCES encounter_types (id),
    CONSTRAINT fk_encounters_modality
        FOREIGN KEY (modality_id) REFERENCES encounter_modalities (id),
    CONSTRAINT fk_encounters_status
        FOREIGN KEY (status_id) REFERENCES encounter_statuses (id),
    CONSTRAINT fk_encounters_created_by
        FOREIGN KEY (created_by) REFERENCES professionals (id),
    CONSTRAINT fk_encounters_updated_by
        FOREIGN KEY (updated_by) REFERENCES professionals (id)
) ENGINE = InnoDB;
