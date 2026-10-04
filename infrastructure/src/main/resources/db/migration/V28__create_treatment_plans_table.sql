CREATE TABLE treatment_plans (
    id CHAR(36) NOT NULL,
    encounter_id CHAR(36) NOT NULL,
    professional_id CHAR(36) NOT NULL,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    treatment_status_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_treatment_plans PRIMARY KEY (id),
    CONSTRAINT fk_treatment_plans_encounter
        FOREIGN KEY (encounter_id) REFERENCES encounters (id),
    CONSTRAINT fk_treatment_plans_professional
        FOREIGN KEY (professional_id) REFERENCES professionals (id),
    CONSTRAINT fk_treatment_plans_status
        FOREIGN KEY (treatment_status_id) REFERENCES treatment_statuses (id)
) ENGINE = InnoDB;
