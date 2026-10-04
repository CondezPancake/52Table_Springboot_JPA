CREATE TABLE mental_status_exams (
    id CHAR(36) NOT NULL,
    encounter_id CHAR(36) NOT NULL,
    appearance TEXT NOT NULL,
    behavior TEXT NOT NULL,
    attitude TEXT NOT NULL,
    consciousness TEXT NOT NULL,
    orientation TEXT NOT NULL,
    attention TEXT NOT NULL,
    memory TEXT NOT NULL,
    speech TEXT NOT NULL,
    mood TEXT NOT NULL,
    affect TEXT NOT NULL,
    thought_process TEXT NOT NULL,
    thought_content TEXT NOT NULL,
    perception TEXT NOT NULL,
    judgment TEXT NOT NULL,
    insight TEXT NOT NULL,
    psychomotor_activity TEXT NOT NULL,
    observations TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by CHAR(36) NOT NULL,
    CONSTRAINT pk_mental_status_exams PRIMARY KEY (id),
    CONSTRAINT fk_mental_status_exams_encounter
        FOREIGN KEY (encounter_id) REFERENCES encounters (id),
    CONSTRAINT fk_mental_status_exams_created_by
        FOREIGN KEY (created_by) REFERENCES professionals (id)
) ENGINE = InnoDB;
