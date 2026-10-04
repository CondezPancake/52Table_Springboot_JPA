CREATE TABLE ai_models (
    id CHAR(36) NOT NULL,
    provider_model_id CHAR(36) NOT NULL,
    name_model VARCHAR(100) NOT NULL,
    model_key VARCHAR(120) NOT NULL,
    input_token_price DECIMAL(12,8) NOT NULL,
    output_token_price DECIMAL(12,8) NOT NULL,
    max_tokens INTEGER NOT NULL,
    context_window INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_ai_models PRIMARY KEY (id),
    CONSTRAINT fk_ai_models_provider
        FOREIGN KEY (provider_model_id) REFERENCES provider_models_ai (id)
) ENGINE = InnoDB;
