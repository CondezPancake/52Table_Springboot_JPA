CREATE TABLE state_regions (
    id CHAR(36) NOT NULL,
    name_region VARCHAR(50) NOT NULL,
    code_region VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    country_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_state_regions PRIMARY KEY (id),
    CONSTRAINT fk_state_regions_country
        FOREIGN KEY (country_id) REFERENCES countries (id)
) ENGINE = InnoDB;
