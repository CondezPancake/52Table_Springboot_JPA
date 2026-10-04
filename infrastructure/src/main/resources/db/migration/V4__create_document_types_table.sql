CREATE TABLE document_types (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_document_types PRIMARY KEY (id),
    CONSTRAINT uk_document_types_code UNIQUE (code)
) ENGINE = InnoDB;
