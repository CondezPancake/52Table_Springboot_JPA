CREATE TABLE provider_models_ai (
    id CHAR(36) NOT NULL,
    name_provider_ai VARCHAR(100) NOT NULL,
    razon_social VARCHAR(150) NOT NULL,
    sitio_web TEXT NOT NULL,
    isActive BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_provider_models_ai PRIMARY KEY (id)
) ENGINE = InnoDB;
