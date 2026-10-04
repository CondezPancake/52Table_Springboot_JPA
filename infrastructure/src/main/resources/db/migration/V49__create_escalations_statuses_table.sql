CREATE TABLE escalations_statuses (
    id CHAR(36) NOT NULL,
    name_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_escalations_statuses PRIMARY KEY (id)
) ENGINE = InnoDB;
