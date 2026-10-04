CREATE TABLE sender_types (
    id CHAR(36) NOT NULL,
    name_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_sender_types PRIMARY KEY (id)
) ENGINE = InnoDB;
