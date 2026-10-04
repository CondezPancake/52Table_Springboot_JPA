CREATE TABLE clinical_notes (
    id CHAR(36) NOT NULL,
    encounter_id CHAR(36) NOT NULL,
    professional_id CHAR(36) NOT NULL,
    subjective TEXT NOT NULL,
    objective TEXT NOT NULL,
    assessment TEXT NOT NULL,
    plan TEXT NOT NULL,
    additional_notes TEXT NOT NULL,
    signed_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_clinical_notes PRIMARY KEY (id),
    CONSTRAINT fk_clinical_notes_encounter
        FOREIGN KEY (encounter_id) REFERENCES encounters (id),
    CONSTRAINT fk_clinical_notes_professional
        FOREIGN KEY (professional_id) REFERENCES professionals (id)
) ENGINE = InnoDB;
