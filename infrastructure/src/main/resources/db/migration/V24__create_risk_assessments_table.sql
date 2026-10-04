CREATE TABLE risk_assessments (
    id CHAR(36) NOT NULL,
    encounter_id CHAR(36) NOT NULL,
    risk_level_id CHAR(36) NOT NULL,
    suicidal_ideation BOOLEAN NOT NULL,
    suicide_plan BOOLEAN NOT NULL,
    suicide_intent BOOLEAN NOT NULL,
    self_harm BOOLEAN NOT NULL,
    harm_to_others BOOLEAN NOT NULL,
    risk_factors TEXT NOT NULL,
    protective_factors TEXT NOT NULL,
    clinical_actions TEXT NOT NULL,
    observations TEXT NOT NULL,
    assessed_at TIMESTAMP NOT NULL,
    assessed_by CHAR(36) NOT NULL,
    CONSTRAINT pk_risk_assessments PRIMARY KEY (id),
    CONSTRAINT fk_risk_assessments_encounter
        FOREIGN KEY (encounter_id) REFERENCES encounters (id),
    CONSTRAINT fk_risk_assessments_risk_level
        FOREIGN KEY (risk_level_id) REFERENCES risk_levels (id),
    CONSTRAINT fk_risk_assessments_assessed_by
        FOREIGN KEY (assessed_by) REFERENCES professionals (id)
) ENGINE = InnoDB;
