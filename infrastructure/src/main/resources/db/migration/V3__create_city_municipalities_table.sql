CREATE TABLE city_municipalities (
    id CHAR(36) NOT NULL,
    name_city VARCHAR(50) NOT NULL,
    code_city VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    region_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_city_municipalities PRIMARY KEY (id),
    CONSTRAINT fk_city_municipalities_region
        FOREIGN KEY (region_id) REFERENCES state_regions (id)
) ENGINE = InnoDB;
