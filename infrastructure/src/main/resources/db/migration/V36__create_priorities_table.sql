CREATE TABLE priorities (
    id CHAR(36) NOT NULL,
    name_priority VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_priorities PRIMARY KEY (id)
) ENGINE = InnoDB;
